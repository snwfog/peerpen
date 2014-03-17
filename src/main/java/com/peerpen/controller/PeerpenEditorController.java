package com.peerpen.controller;

import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: {@see <a href="http://github.com/snwfog">Charles Yang</a>}
 * @since: 3/7/2014
 *
 */
public class PeerpenEditorController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Map<String, String> map = (Map<String, String>) request.getAttribute( "parameters" );

        request.getRequestDispatcher( "/view/ppeditor.jsp" ).forward( request, response );
    }
}
