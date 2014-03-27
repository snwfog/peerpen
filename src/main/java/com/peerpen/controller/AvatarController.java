/**
 * Created by Zearf on 1/7/2014.
 */
package com.peerpen.controller;

import com.peerpen.model.Peer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvatarController extends HttpServlet {

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Peer sessionPeer = (Peer) request.getAttribute( "sessionUser" );
        //For testing purposes
        if ( sessionPeer == null ) {
            sessionPeer = new Peer().find( 3 );
            sessionPeer.getAvatar().cropAvatar( request );
        }
        //Run normally
        else {
            sessionPeer.getAvatar().cropAvatar( request );
            request.getRequestDispatcher( "/view/profile.jsp" ).forward( request, response );
        }
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.getRequestDispatcher( "/view/avatar.jsp" ).forward( request, response );
    }

}
