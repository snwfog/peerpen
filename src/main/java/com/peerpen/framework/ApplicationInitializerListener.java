package com.peerpen.framework;


import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class ApplicationInitializerListener implements ServletContextListener {

    static final Logger logger = LoggerFactory.getLogger( ApplicationInitializerListener.class );
    private ServletContextEvent avatarPath;

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        logger.warn( "Set application global routes" );
        setAllRoutes( event );
        logger.warn( "Set exempt routes" );
        setExemptRoutes( event );
        logger.warn( "Set transient routes" );
        setTransientRoutes( event );
        logger.warn( "Set application secret" );
        setApplicationSecret( event );
        //setJspGlobalVariables( event );
        logger.warn( "Set avatar directory" );
        setAvatarDir( event );
    }

    private void setAvatarDir( ServletContextEvent event ) {
        String avatarDir = event.getServletContext().getInitParameter( "avatar-dir" );
        event.getServletContext().setAttribute( "avatarDir", avatarDir );
    }

    /**
     * Set the secret of the application for internal routing
     * @param event
     */
    private void setApplicationSecret( ServletContextEvent event ) {
        String appSecret = event.getServletContext().getInitParameter( "app-secret" );
        event.getServletContext().setAttribute( "appSecret", appSecret );
    }
   
    /**
     * Will set into servlet context all possible routes
     * @param event
     */
    private void setAllRoutes( ServletContextEvent event ) {
        Yaml yml = new Yaml();
        String fileRelativePath = event.getServletContext().getInitParameter( "resource" );
        InputStream fis = event.getServletContext().getResourceAsStream( "/WEB-INF/classes/" + fileRelativePath );
        logger.info( "Reading routes information from YAML (" + fis + ")" );
        List m = (List) yml.load( fis );
        ServletRoute route = new ServletRoute( m );
        Set<String> allRoutes = route.getAllRoutes();
        event.getServletContext().setAttribute( "servletRoute", route );
        event.getServletContext().setAttribute( "allRoutes", allRoutes );
    }

    private void setExemptRoutes( ServletContextEvent event ) {
        String safeRoutes = event.getServletContext().getInitParameter( "exempt-routes" );
        Set<String> safeRoutesSet =
                new HashSet<String>( Arrays.asList( safeRoutes.replaceAll( "[^\\S\\n]", "" ).split( "\\n" ) ) );

        event.getServletContext().setAttribute( "exemptRoutes", safeRoutesSet );
    }

    private void setTransientRoutes( ServletContextEvent event ) {
        String safeRoutes = event.getServletContext().getInitParameter( "transient-routes" );
        Set<String> transientRoutes =
                new HashSet<String>( Arrays.asList( safeRoutes.replaceAll( "[^\\S\\n]", "" ).split( "\\n" ) ) );

        event.getServletContext().setAttribute( "transientRoutes", transientRoutes );
    }

    private void setJspGlobalVariables( ServletContextEvent event ) {
        try {
            URI classPath = ClassLoader.getSystemResource( "" ).toURI();
            logger.info( "Setting application class path (" + classPath + ")" );
            event.getServletContext().setAttribute( "classPath", classPath.toString() );
        } catch ( URISyntaxException e ) {
            logger.error( "Bad application class path" );
        }


    }

    //private static String pattern = "/{0}";
    //private static MessageFormat format = new MessageFormat( pattern );
    //
    //public Set<String> getAllRoutes( List list ) {
    //    Set<String> allRoutes = new LinkedHashSet<String>();
    //    for ( Object m : list ) {
    //        StringBuffer sb = new StringBuffer();
    //        if ( m instanceof Map ) {
    //            StringBuffer newSb = new StringBuffer( sb );
    //            getRoute( (Map) m, newSb, allRoutes );
    //        } else {
    //            sb.append( format.format( pattern, m ) );
    //            allRoutes.add( sb.toString() );
    //        }
    //    }
    //
    //    return allRoutes;
    //}
    //
    //private StringBuffer getRoute( Map m, StringBuffer sb, Set<String> allRoutes ) {
    //    for ( Object ob : m.keySet() ) {
    //        sb.append( format.format( pattern, ob ) );
    //        allRoutes.add( sb.toString() );
    //        if ( m.get( ob ) instanceof List ) {
    //            List list = (List) m.get( ob );
    //            for ( Object listObject : list ) {
    //                if ( listObject instanceof Map ) {
    //                    StringBuffer newSb = new StringBuffer( sb );
    //                    getRoute( (Map) listObject, newSb, allRoutes );
    //                } else {
    //                    sb.append( format.format( pattern, listObject ) );
    //                    allRoutes.add( sb.toString() );
    //                }
    //            }
    //        }
    //    }
    //
    //    return sb;
    //}

    @Override
    public void contextDestroyed( ServletContextEvent event ) {

    }
}
