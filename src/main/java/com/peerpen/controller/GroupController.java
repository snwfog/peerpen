package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupController extends GenericApplicationServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        super.doGet( request, response );
        Map<String, Object> parameters = internalRequest.getParametersMap();
        Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap( parameters );
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Group urlGroup = (Group) modelMap.get( "group" );
        List<Group> groups = new Group().getSortedGroups( "az", sessionUser.getId() );
        if ( urlGroup != null ) {
            request.setAttribute( "group", urlGroup );
            request.getRequestDispatcher( "/view/group.jsp" ).forward( request, response );
        } else {
            request.setAttribute( "groups", groups );
            request.setAttribute( "sort", "az" );
            request.getRequestDispatcher( "/view/groups.jsp" ).forward( request, response );
        }
    }

    protected void doPut( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );
        String sort = parameters.get( "sort" );
        List<Group> groups = new Group().getSortedGroups( sort, sessionUser.getId() );
        request.setAttribute( "groups", groups );
        request.setAttribute( "sort", sort );
        request.getRequestDispatcher( "/view/groups.jsp" ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Group group = new Group();
        group.setGroupName( parameters.get( "name" ) );
        group.setDescription( parameters.get( "description" ) );
        group.setAdminId( sessionUser.getId() );
        group.addPeer( sessionUser );
        group.save();

        request.setAttribute( "group", group );
        response.sendRedirect( "/group/" + group.getId() );
    }

    protected void doDelete( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );
        Group group = new Group().find( Integer.parseInt( parameters.get( "groupid" ) ) );
        Peer p = new Peer().find( Integer.parseInt( parameters.get( "peerid" ) ) );

        group.removePeer( p );
        group.update();
        response.sendRedirect( request.getHeader( "referer" ) );
    }

}
