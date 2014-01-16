package com.peerpen.framework;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by snw on 1/15/2014.
 */
public class RouteController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( RouteController.class );
    private static String pattern = "/{0}";
    private static MessageFormat format = new MessageFormat(pattern);

    public List<String> getAllRoutes( List list ) {
        List<String> allRoutes = new ArrayList<String>();
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

    private StringBuffer getRoute( Map m, StringBuffer sb, List<String> allRoutes ) {
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
        List<String> allRoutes = this.getAllRoutes( routeList );
        for ( String route : allRoutes ) {
            logger.info( route );
        }
    }
}
