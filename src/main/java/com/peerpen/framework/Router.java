package com.peerpen.framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Router extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( Router.class );

    private boolean isSafeRoutes( String requestURI ) {
        for ( String route : (Set<String>) this.getServletContext().getAttribute( "safeRoutes" ) ) {
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


    private boolean isPermissibleRoutes( String stringQuery ) {

        return false;
    }

    private void redirect( HttpServletRequest request, HttpServletResponse response, int code, String path,
            String reason ) {
        try {

            RequestDispatcher dispatcher = request.getRequestDispatcher( path );
            response.setStatus( code );
            request.setAttribute( "reason", reason );
            dispatcher.forward( request, response );
            logger.warn("Denied user trying to access an invalid path " + request.getRequestURI() );

        } catch ( IOException e ) {
            logger.error( "Could not locate path " + path );

        } catch ( ServletException e ) {
            logger.error( "Could not forward user to " + path );
        }
    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response, String path, String reason)
    {
        redirect( request, response, 404, path, reason );
    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response)
    {
        redirect( request, response, 404, "/error", "Something went wrong with accessing this page." );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) {
        String rURI = request.getRequestURI();
        String appPath =
                this.getServletContext().getRealPath( "" ); // /Users/snw/Dropbox/prog/java/peerpen/target/peerpen
        if ( isSafeRoutes( rURI ) ) {
            try {
                InputStream fis = this.getServletContext().getResourceAsStream( rURI );
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
                this.redirectError( request, response );
                logger.error( e.toString() );
            } catch ( IOException e ) {
                this.redirectError( request, response);
                logger.error( e.toString() );
            }
        }
        else if ( isPermissibleRoutes( rURI ))
        {
            // Let the user go through
        }
        else
        {
            this.redirectError( request, response);
        }



    }


    protected void doPost( HttpServletRequest request, HttpServletResponse response ) {

    }
}
