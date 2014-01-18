package com.peerpen.framework.filter;

import com.google.common.collect.ImmutableMap;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.HttpException;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.NonPermissibleRoute;
import com.peerpen.framework.exception.NotLoggedInException;
import com.peerpen.framework.exception.TooManyUrlNestingException;
import com.peerpen.framework.exception.UserNotFoundException;
import com.peerpen.model.Peer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.apache.commons.lang3.StringUtils;
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
        logger.info( "Serving a request for " + ((HttpServletRequest) request).getRequestURL() );
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String rURI = httpRequest.getRequestURI();
        String appPath =
                fc.getServletContext().getRealPath( "" ); // /Users/snw/Dropbox/prog/java/peerpen/target/peerpen
        try {
            if ( isInternalPreauthorizedForward( httpRequest, (HttpServletResponse) response ) ) {
                logger.warn( "Received an interval request for " + httpRequest.getRequestURI() );
                chain.doFilter( request, response );
                //try {
                //    (request.getRequestDispatcher( rURI )).forward( request, response );
                //} catch ( ServletException e ) {
                //    logger.error( "Failed internal forward to path " + rURI );
                //} catch ( IOException e ) {
                //    logger.error( "Failed internal forward to path " + rURI );
                //}
            } else if ( isSafeRoutes( rURI ) ) {
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
                } catch ( IOException e ) {
                    this.redirectError( httpRequest, (HttpServletResponse) response );
                    logger.error( e.toString() );
                }
            } else if ( isPermissibleRoutes( rURI ) ) {

                Map<String, String> parametersMap = ImmutableMap.of();


                // Let the user go through
                HttpSession session = httpRequest.getSession();

                if ( isAjaxRequest( httpRequest ) ) {
                    logger.info( "Request is an application Ajax request" );
                    request.setAttribute( "requestType", "applicationJson" );
                    request.setAttribute( "requestData", sanitizeJsonData( httpRequest ) );
                }

                try {
                    logger.info( "Trying to locate and to validate a peer with sessionId of " + session.getId() );
                    Map<String, Object> condition = ImmutableMap.of( "sessionId", (Object) session.getId() );
                    Peer p = (new Peer()).find( condition );
                    if ( p == null ) {
                        throw new UserNotFoundException( session );
                    }
                    if ( session.isNew() && p.getSessionId().equals( session.getId() ) ) {
                        (request.getRequestDispatcher( httpRequest.getRequestURL().toString() )).forward( request,
                                response );
                    } else {
                        throw new NotLoggedInException( session );
                    }
                } catch ( UserNotFoundException e ) {
                    this.redirectUnauthorized( httpRequest, (HttpServletResponse) response );
                } catch ( NotLoggedInException e ) {
                    request.setAttribute( "exception", e );
                    this.redirectError( httpRequest, (HttpServletResponse) response );
                } catch ( ServletException e ) {
                    logger.error( "Cannot find servlet" );
                } catch ( IOException e ) {
                    logger.error( "Cannot find proper jsp" );
                }
            } else {
                this.redirectError( httpRequest, (HttpServletResponse) response );
            }
        } catch ( MissingArgumentException | NonPermissibleRoute | TooManyUrlNestingException e ) {
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
                "application/x-www-form-urlencoded; charset=UTF-8".contains( applicationRequestHeader );
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

        for ( String route : (Set<String>) fc.getServletContext().getAttribute( "exemptRoutes" ) ) {
            if ( requestURI.indexOf( route ) == 0 ) {
                return true;
            }
        }
        return false;
    }

    private String detectMimeType( String URI ) {
        int dotLocation = URI.lastIndexOf( "." );
        String type = URI.substring( dotLocation + 1, URI.length() );
        return "text/" + type;
    }


    private boolean isPermissibleRoutes( String stringQuery )
            throws NonPermissibleRoute, TooManyUrlNestingException, MissingArgumentException {
        String url = stringQuery.substring( 0
                , stringQuery.contains( "?" ) ? stringQuery.indexOf( "?" ) : stringQuery.length() );
        String[] strippedUrl = url.split( "[/0-9/]+" );
        String strippedJoinedUrl = StringUtils.join( strippedUrl, "/" );
        Set<String> routes = (Set<String>) fc.getServletContext().getAttribute( "allRoutes" );
        if ( !routes.contains( strippedJoinedUrl ) ) {
            throw new NonPermissibleRoute( "Route does not exists " + stringQuery );
        }

        // Greater than 4 nesting will be way too much mkay?
        if ( strippedUrl.length > 5 ) {
            throw new TooManyUrlNestingException( "Too many nesting found: " + strippedUrl.length );
        }

        //if ( strippedUrl.length % 2 != 0 ) {
        //    throw new MissingArgumentException( "Missing argument, found " + strippedUrl.length );
        //}

        return true;
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
        HttpException e = (HttpException) request.getAttribute( "exception" );
        if ( e != null ) {
            redirect( request, response, e.getStatusCode(), "/error", e.toString() );
        } else {
            redirect( request, response, 404, "/error", "Something went wrong with accessing this page." );
        }

    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response, HttpException exception ) {
        redirect( request, response, exception.getStatusCode(), "/error", exception.toString() );
    }

    public void redirectUnauthorized( HttpServletRequest request, HttpServletResponse response ) {
        redirect( request, response, 401, "/error", "Unauthorized access." );
    }
}


















