package com.peerpen.controller;

import com.peerpen.model.Feedable;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        throw new UnsupportedOperationException( this.getClass().toString() );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        //User can only see their own feed
        Integer peerId = ((Peer)request.getAttribute("sessionUser")).getId();
        Peer peer = new Peer().find( peerId );
        List<Feedable> data = Feedable.getFeed( peerId );
        request.setAttribute( "feedableList", data );
        request.setAttribute( "peerObject", peer );
        request.getRequestDispatcher( "/view/feed/feed.jsp" ).forward( request, response );
    }

}
