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

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        setGlobalRoutes( event );
        setSafeRoutes( event );
        //setJspGlobalVariables( event );
    }


    /**
     * Will set into servlet context all possible routes
     * @param event
     */
    private void setGlobalRoutes( ServletContextEvent event ) {
        Yaml yml = new Yaml();
        String fileRelativePath = event.getServletContext().getInitParameter( "resource" );
        InputStream fis = event.getServletContext().getResourceAsStream( "/WEB-INF/classes/" + fileRelativePath );
        logger.info( "Reading routes information from YAML (" + fis + ")" );
        List m = (List) yml.load( fis );
        event.getServletContext().setAttribute( "routes", m );
    }

    private void setSafeRoutes( ServletContextEvent event ) {
        String safeRoutes = event.getServletContext().getInitParameter( "safe-routes" );
        Set<String> safeRoutesSet = new HashSet<String>( Arrays.asList( safeRoutes.split( "\\s" ) ) );

        event.getServletContext().setAttribute( "safeRoutes", safeRoutesSet );
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

    @Override
    public void contextDestroyed( ServletContextEvent event ) {

    }
}
