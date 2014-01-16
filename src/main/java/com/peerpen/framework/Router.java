package com.peerpen.framework;

import com.sun.xml.internal.bind.v2.model.runtime.RuntimeReferencePropertyInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;

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
                response.setContentType( this.detectMimeType( rURI ));
                PrintWriter pw = response.getWriter();
                byte[] bytes = new byte[fis.available()];
                fis.read( bytes );
                response.setContentLength( bytes.length );
                pw.print( new String( bytes ) );
                pw.flush();
                pw.close();
            } catch ( FileNotFoundException e ) {
                logger.error( e.toString() );
            } catch ( IOException e ) {
            }
        }


    }


    protected void doPost( HttpServletRequest request, HttpServletResponse response ) {

    }
}
