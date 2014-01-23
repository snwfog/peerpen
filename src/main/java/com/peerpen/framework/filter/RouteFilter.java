package com.peerpen.framework.filter;

import com.google.common.collect.ImmutableMap;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.ServletRoute;
import com.peerpen.framework.exception.HttpException;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.NonPermissibleRoute;
import com.peerpen.framework.exception.NotLoggedInException;
import com.peerpen.framework.exception.ParameterCollision;
import com.peerpen.framework.exception.TooManyUrlNestingException;
import com.peerpen.model.Peer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteFilter implements Filter {

    private FilterConfig fc;
    static final Logger logger = LoggerFactory.getLogger( RouteFilter.class );

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        this.fc = filterConfig;
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String rURI = httpRequest.getRequestURI();
        String appPath =
                fc.getServletContext().getRealPath( "" ); // /Users/snw/Dropbox/prog/java/peerpen/target/peerpen

        logger.info( "Incoming request for " + ((HttpServletRequest) request).getRequestURL() );

        try {

            if ( isSafeRoutes( rURI ) ) {
                try {
                    InputStream fis = fc.getServletContext().getResourceAsStream( rURI );
                    if ( fis == null ) {
                        throw new FileNotFoundException( "Could not locate file " + rURI );
                    }
                    // Set to general plain, could be made better by looking at file extension
                    // http://stackoverflow.com/questions/7380468/write-an-html-page-in-the-servlet-response-properly
                    response.setContentType( this.detectMimeType( rURI ) );
                    PrintWriter pw = response.getWriter();
                    byte[] bytes = new byte[fis.available()];
                    fis.read( bytes );
                    response.setContentLength( bytes.length );
                    pw.print( new String( bytes ) );
                    pw.flush();
                    pw.close();
                } catch ( FileNotFoundException e ) {
                    //this.redirectError( httpRequest, (HttpServletResponse) response );
                    // Maybe its a page that we are trying to go?
                    chain.doFilter( httpRequest, response );
                    logger.error( e.toString() );
                    logger.error( "Assume to be root" );
                } catch ( IOException e ) {
                    this.redirectError( httpRequest, (HttpServletResponse) response );
                    logger.error( e.toString() );

                }

            } else if ( isPermissibleRoutes( rURI ) ) {
                if ( isInternalPreauthorizedForward( httpRequest, (HttpServletResponse) response ) ) {
                    logger.warn( "Received request is an internal request for " + httpRequest.getRequestURI() );
                    logger.warn( "Going to assume that you know what you're doing... forwarding you right away" );
                    chain.doFilter( request, response );
                    //try {
                    //    (request.getRequestDispatcher( rURI )).forward( request, response );
                    //} catch ( ServletException e ) {
                    //    logger.error( "Failed internal forward to path " + rURI );
                    //} catch ( IOException e ) {
                    //    logger.error( "Failed internal forward to path " + rURI );
                    //}

                }


                // Let the user go through
                HttpSession session = httpRequest.getSession();

                if ( isAjaxRequest( httpRequest ) ) {
                    logger.info( "Received request is an application Ajax request for " + httpRequest.getRequestURI() );
                    // TODO: Implement the logging for Json data
                    logger.info( "Data submitted to servlet is " + " NOT YET IMPLEMENTED" );
                    request.setAttribute( "requestType", "applicationJson" );
                    request.setAttribute( "requestData", sanitizeJsonData( httpRequest ) );
                }

                try {
                    logger.info( "Trying to locate and to validate a peer with sessionId of " + session.getId() );
                    Map<String, Object> condition = ImmutableMap.of( "sessionId", (Object) session.getId() );
                    Peer user = null;
                    if ( (user = Peer.isLoggedIn( httpRequest )) != null && Peer.isValidSession( user, session ) ) {
                        logger.info( "Located user " + user );
                        request.setAttribute( "peer", user );
                        // TODO: Validate the rest of the resource along the path
                        // TODO: Inject the parameter map
                        // TODO: Inject the URL tunnel parameters as well
                        // Take the last resource and call up on that particular controller and preauthorize
                        String[] resources = rURI.replaceFirst( "/", "" ).split( "/" );
                        Map<String, String> parametersMap = new LinkedHashMap<>();
                        for ( int i = 0; i < resources.length; i++ ) {
                            if ( i + 1 < resources.length && this.isNumeric( resources[i + 1] ) ) {
                                parametersMap.put( resources[i], resources[++i] );
                            } else {
                                parametersMap.put( resources[i], null );
                            }
                        }

                        Enumeration enumeration = httpRequest.getParameterNames();
                        while ( enumeration.hasMoreElements() ) {
                            String key = (String) enumeration.nextElement();
                            String oldValue = parametersMap.put( key, httpRequest.getParameter( key ) );
                            if ( oldValue != null ) {
                                throw new ParameterCollision( key );
                            }
                        }

                        logger.info( "Logging parameter linked list map of " + parametersMap.toString() );
                        request.setAttribute( "parameters", parametersMap );

                        String lastController = null;
                        if ( resources.length % 2 == 0 && resources.length != 0 ) {
                            lastController = resources[resources.length - 2];
                        } else {
                            lastController = resources[resources.length - 1];
                        }

                        //chain.doFilter( request, response );
                        String internalRoute = MessageFormat.format( "/{0}", lastController );
                        request.setAttribute( "internalRequestURI", internalRoute );
                        request.setAttribute( "externalRequestURI", rURI );
                        request.setAttribute( "controller", internalRoute );
                        logger.info( "Forwarding an external request of " + rURI + " to an internal controller of " +
                                internalRoute );
                        request.getRequestDispatcher( internalRoute ).forward( request, response );
                        //throw new UserNotFoundException( session );
                    } else {
                        throw new NotLoggedInException( session );
                    }
                } catch ( NotLoggedInException e ) {
                    request.setAttribute( "exception", e ); // TODO: Change this signature
                    this.redirectError( httpRequest, (HttpServletResponse) response );
                } catch ( ServletException e ) {
                    logger.error( "Servlet crashed from coding error", e );
                    this.redirectError( httpRequest, (HttpServletResponse) response, e );
                } catch ( IOException e ) {
                    logger.error( "Cannot find proper jsp", e );
                    this.redirectError( httpRequest, (HttpServletResponse) response, e );
                } catch ( ParameterCollision e ) {
                    logger.error( "Conflict for parameter key", e );
                    this.redirectError( httpRequest, (HttpServletResponse) response, e );
                }
            } else {
                this.redirectError( httpRequest, (HttpServletResponse) response );
            }
        } catch ( MissingArgumentException | NonPermissibleRoute |
                TooManyUrlNestingException e ) {
            this.redirectError( (HttpServletRequest) request, (HttpServletResponse) response, e );
        }

    }

    // TODO: Implement Map<String, String> or JsonElement
    private String sanitizeJsonData( HttpServletRequest httpRequest ) {
        logger.info( "Sanitizing Ajax data" );
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = httpRequest.getReader();
            while ( (line = reader.readLine()) != null ) {
                sb.append( StringEscapeUtils.escapeHtml4( line ) );
            }
        } catch ( IOException e ) {
            logger.error( "Error parsing application Json data" );
        }

        // Serializing into Json object
        return sb.toString();
    }

    private boolean isAjaxRequest( HttpServletRequest request ) {
        String applicationRequestHeader = request.getHeader( "Content-Type" );
        return applicationRequestHeader != null &&
                "application/json; charset=utf-8".contains( applicationRequestHeader );
    }

    @Override
    public void destroy() {

    }

    public boolean isInternalPreauthorizedForward( HttpServletRequest request, HttpServletResponse response ) {
        if ( request instanceof InternalHttpServletRequest ) {
            InternalHttpServletRequest internalRequest = (InternalHttpServletRequest) request;
            String appSecret = (String) internalRequest.getAttribute( "appSecret" );
            return (appSecret != null) && appSecret.equals( fc.getServletContext().getInitParameter( "app-secret" ) );

        }

        return false;
    }

    private boolean isSafeRoutes( String requestURI ) {
        if ( requestURI.equals( "/" ) ) {
            return true;
        }

        boolean isSafeRoutes = false;
        for ( String route : (Set<String>) fc.getServletContext().getAttribute( "exemptRoutes" ) ) {
            isSafeRoutes = isSafeRoutes || (requestURI.contains( route ));
        }
        return isSafeRoutes;
    }

    private String detectMimeType( String URI ) {
        int dotLocation = URI.lastIndexOf( "." );
        String type = URI.substring( dotLocation + 1, URI.length() );
        return "text/" + type;
    }


    private boolean isPermissibleRoutes( String stringQuery )
            throws NonPermissibleRoute, TooManyUrlNestingException, MissingArgumentException {
        String url = stringQuery.substring( 0,
                stringQuery.contains( "?" ) ? stringQuery.indexOf( "?" ) : stringQuery.length() );
        String[] strippedUrl = url.replaceFirst( "/", "" ).split( "[/0-9/]+" );
        ServletRoute route = (ServletRoute) fc.getServletContext().getAttribute( "servletRoute" );
        //String strippedJoinedUrl = StringUtils.join( strippedUrl, "/" );
        //Set<String> routes = (Set<String>) fc.getServletContext().getAttribute( "allRoutes" );
        if ( !route.isValidRoute( strippedUrl ) ) {
            //if ( !routes.contains( strippedJoinedUrl ) ) {
            throw new NonPermissibleRoute( "Route does not exists " + stringQuery );
        }

        // Greater than 4 nesting will be way too much mkay?
        if ( strippedUrl.length > 5 ) {
            throw new TooManyUrlNestingException( "Too many nesting detected: " + strippedUrl.length );
        }

        //if ( strippedUrl.length % 2 != 0 ) {
        //    throw new MissingArgumentException( "Missing argument, found " + strippedUrl.length );
        //}

        return true;
    }

    private void redirectError( HttpServletRequest request, HttpServletResponse response, Throwable e ) {
        request.setAttribute( "exception", e );
        if ( e instanceof HttpException ) {
            HttpException exc = (HttpException) e;
            this.redirect( request, response, exc.getStatusCode(), "/error", e.toString() );
        } else {
            this.redirectError( request, response );
        }
    }

    private void redirect( HttpServletRequest request, HttpServletResponse response, int code, String path,
            String reason ) {
        try {

            RequestDispatcher dispatcher = request.getRequestDispatcher( path );
            response.setStatus( code );
            request.setAttribute( "reason", reason );
            request.setAttribute( "errorCode", code );
            dispatcher.forward( request, response );
            logger.warn( "Denied user trying to access an invalid path " + request.getRequestURI() );

        } catch ( IOException e ) {
            logger.error( "Could not locate path " + path );

        } catch ( ServletException e ) {
            logger.error( "Could not forward user to " + path );
        }
    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response ) {
        Throwable e = (Throwable) request.getAttribute( "exception" );
        if ( e != null && e instanceof HttpException ) {
            redirect( request, response, ((HttpException) e).getStatusCode(), "/error", e.toString() );
        } else {
            redirect( request, response, 404, "/error", "Something went wrong when accessing this page." );
        }

    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response, HttpException exception ) {
        redirect( request, response, exception.getStatusCode(), "/error", exception.toString() );
    }

    public void redirectUnauthorized( HttpServletRequest request, HttpServletResponse response ) {
        redirect( request, response, 401, "/error", "Unauthorized access." );
    }

    private boolean isNumeric( String str ) {
        return str.matches( "-?\\d+(\\.\\d+)?" );
    }

}


















