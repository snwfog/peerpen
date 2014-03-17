package com.peerpen.controller;

import com.peerpen.model.Changeset;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentController extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );
        Peer peer = new Peer().find( Integer.parseInt( parameters.get( "peerid" ) ) );
        Document document = new Document().find( Integer.parseInt( parameters.get( "docid" ) ) );
        String message = parameters.get( "comment" );
        document.createComment( message, peer );
        request.setAttribute( "document", document );
        response.sendRedirect( request.getHeader( "referer" ) );
    }

    //  Create changeset comment
    protected void doPut( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );
        Peer peer = new Peer().find( Integer.parseInt( parameters.get( "peerid" ) ) );
        Document document = new Document().find( Integer.parseInt( parameters.get( "docid" ) ) );
        Changeset changeset = new Changeset().find( Integer.parseInt( parameters.get( "changesetid" ) ) );

        String message = parameters.get( "comment" );
        changeset.createComment( message, peer );
        request.setAttribute( "document", document );
        response.sendRedirect( request.getHeader( "referer" ) );
    }

    //  Delete comment
    protected void doDelete( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute( "parameters" );

        Peer peer = new Peer().find( Integer.parseInt( parameters.get( "peerid" ) ) );
        Document document = new Document().find( Integer.parseInt( request.getParameter( "docid" ) ) );

        Comment comment = new Comment().find( Integer.parseInt( request.getParameter( "commentid" ) ) );
        comment.destroy();

        request.setAttribute( "document", document );
        request.setAttribute( "user", peer );
        response.sendRedirect( request.getHeader( "referer" ) );
    }
}
