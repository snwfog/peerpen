/**
 * Created by Zearf on 1/7/2014.
 */
package com.peerpen.controller;

import com.peerpen.model.Avatar;
import com.peerpen.model.Peer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvatarController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( AvatarController.class );

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Peer sessionPeer = (Peer) request.getAttribute( "sessionUser" );
        sessionPeer.getAvatar().cropAvatar( request );
        request.getRequestDispatcher( "/peer/" + sessionPeer.getId() + "/profile/avatar" ).forward( request, response );
    }


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.getRequestDispatcher( "/view/avatar.jsp" ).forward( request, response );
    }

}
