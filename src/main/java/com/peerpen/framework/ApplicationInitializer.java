package com.peerpen.framework;


import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.yaml.snakeyaml.Yaml;

public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        Yaml yml = new Yaml();
        InputStream fis = this.getClass().getResourceAsStream( "/config/resource.yml" );
        Map m = (Map) yml.load( fis );

        event.getServletContext().setAttribute( "routes", m );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {

    }
}
