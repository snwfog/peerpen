package com.peerpen.framework;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This controller will show all the available and permissible routes of this web application
 * by visiting the page /routes
 */
public class RouteController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( RouteController.class );

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        Set<String> allRoutes = (Set<String>) this.getServletContext().getAttribute( "allRoutes" );

        logger.info( "Here are all of the permissible routes: " );
        for ( String route : allRoutes ) {
            logger.info( route );
        }

        req.setAttribute( "routes", allRoutes );

        RequestDispatcher dispatcher = req.getRequestDispatcher( "/view/routes.jsp" );
        dispatcher.forward( req, resp );
    }
}
