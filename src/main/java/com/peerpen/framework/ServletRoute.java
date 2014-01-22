package com.peerpen.framework;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class ServletRoute {

    int size; // Total number of allowable routes
    List<RouteNode> topLevelRoutes;

    public ServletRoute( List routeList ) {
        topLevelRoutes = new ArrayList<>();
        for ( Object m : routeList ) {
            topLevelRoutes.add( new RouteNode( m ) );
        }
    }

    public Set<String> getAllRoutes() {
        Set<String> allRoutes = new LinkedHashSet<>();
        for ( RouteNode topNode : topLevelRoutes ) {
            Deque<String> routes = new ArrayDeque<>();
            fetchPath( allRoutes, routes, topNode );
        }
        return allRoutes;
    }

    private void fetchPath( Set<String> allRoutes, Deque<String> routes, RouteNode node ) {
        // Push the path into the routes
        routes.addLast( node.path );
        allRoutes.add( "/" + StringUtils.join( routes.toArray( new String[routes.size()] ), "/" ) );

        for ( RouteNode child : node.getChildren() ) {
            fetchPath( allRoutes, routes, child );
        }

        routes.removeFirst();
    }

    public boolean isValidRoute( String route ) {
        return isValidRoute( route.replaceFirst( "/", "" ).split( "[/0-9/]+" ) );
    }

    public boolean isValidRoute( String[] routes ) {
        int idx = 0;
        for ( RouteNode node : topLevelRoutes ) {
            if ( routes[idx].matches( node.path ) ) {
                return isValidRoute( routes, ++idx, node );
            }
        }

        return false;
    }

    public boolean isValidRoute( String[] routes, int i, RouteNode node ) {
        if ( i == routes.length ) {
            return true;
        }
        for ( RouteNode n : node.getChildren() ) {
            if ( routes[i].matches( n.path ) ) {
                return isValidRoute( routes, ++i, node );
            }
        }

        return false;
    }

    private class RouteNode {

        String path;
        List<RouteNode> children = new ArrayList<>();

        public RouteNode( Object m ) {
            size++;
            addChildRoute( m );
        }

        private void addChildRoute( Object r ) {
            if ( r instanceof Map ) {
                for ( Object key : ((Map) r).keySet() ) {
                    //path = StringEscapeUtils.escapeJava( StringEscapeUtils.escapeJava( (String) key ) );
                    path = (String) key;
                    Object value = ((Map) r).get( key );
                    if ( value instanceof List ) {
                        for ( Object m : (List) value ) {
                            children.add( new RouteNode( m ) );
                        }
                    } else {
                        children.add( new RouteNode( value ) );
                    }
                }
            } else {
                //path = StringEscapeUtils.escapeJava( StringEscapeUtils.escapeJava( (String) r ) );
                path = (String) r;
            }
        }


        public List<RouteNode> getChildren() {
            return children;
        }

        public int getChildrenCount() {
            return children.size();
        }


    }

}
