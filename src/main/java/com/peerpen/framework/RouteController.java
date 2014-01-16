package com.peerpen.framework;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
    private static String pattern = "/{0}";
    private static MessageFormat format = new MessageFormat(pattern);

    public Set<String> getAllRoutes( List list ) {
        Set<String> allRoutes = new LinkedHashSet<String>();
        for ( Object m : list ) {
            StringBuffer sb = new StringBuffer();
            if ( m instanceof Map ) {
                StringBuffer newSb = new StringBuffer( sb );
                getRoute( (Map) m, newSb, allRoutes );
            } else {
                sb.append( format.format( pattern, m ) );
                allRoutes.add( sb.toString() );
            }
        }

        return allRoutes;
    }

    private StringBuffer getRoute( Map m, StringBuffer sb, Set<String> allRoutes ) {
        for ( Object ob : m.keySet() ) {
            sb.append( format.format( pattern, ob ) );
            allRoutes.add( sb.toString() );
            if ( m.get( ob ) instanceof List ) {
                List list = (List) m.get( ob );
                for ( Object listObject : list ) {
                    if ( listObject instanceof Map ) {
                        StringBuffer newSb = new StringBuffer( sb );
                        getRoute( (Map) listObject, newSb, allRoutes );
                    } else {
                        sb.append( format.format( pattern, listObject ) );
                        allRoutes.add( sb.toString() );
                    }
                }
            }
        }

        return sb;
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        List routeList = (List) this.getServletContext().getAttribute( "routes" );
        Set<String> allRoutes = this.getAllRoutes( routeList );

        logger.info("Here are all of the permissible routes: ");
        for ( String route : allRoutes ) {
            logger.info( route );
        }

        req.setAttribute( "routes", allRoutes );
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/route.jsp");
        dispatcher.forward(req, resp);
    }
}
