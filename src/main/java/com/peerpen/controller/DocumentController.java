package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationController;
import com.peerpen.framework.ServletPath;
import com.peerpen.model.Changeset;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServletPath("/peer/document")
public class DocumentController extends GenericApplicationController {

    static final Logger logger = LoggerFactory.getLogger( DocumentController.class );

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Peer peer = (Peer) session.getAttribute( "user" );
        //Document document = new Document().find( Integer.parseInt( request.getParameter( "docId" ) ) );
        Peer peer = new Peer().find(2);
        session.setAttribute( "user", peer );

        Document document = new Document().find(1);
        List<Comment> comments = document.getDocumentCommentsByOrder( document.getId() );
        List<Changeset> changesets = document.getChangesets();

        request.setAttribute( "comments", comments );
        request.setAttribute( "document", document );
        request.getRequestDispatcher( "/document" ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        if ( request.getParameter( "_method" ) != null ) {
            if ( request.getParameter( "_method" ).contentEquals( "_delete" ) ) {
                doDelete( request, response );
            }
            if ( request.getParameter( "_method" ).contentEquals( "_add_comment_to_changeset" ) ) {
                commentOnChangeset( request, response );
            }
        } else {
            HttpSession session = request.getSession();
            Peer peer = (Peer) session.getAttribute( "user" );
            Document document = new Document().find( Integer.parseInt( request.getParameter( "docId" ) ) );


            Comment comment = new Comment();
            comment.setMessage( request.getParameter( "comment" ).toString() );
            comment.setName( peer.getFirstName() + " " + peer.getLastName() );
            comment.setPeerId( peer.getId() );
            comment.setDocumentId( document.getId() );

            comment.save();

            List<Comment> comments = document.getDocumentCommentsByOrder( document.getId() );

            request.setAttribute( "comments", comments );
            request.setAttribute( "document", document );
            request.getRequestDispatcher( "/document" ).forward( request, response );
        }
    }

    /*
     * Never called explicitly
     */
    protected void doDelete( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Peer peer = (Peer) session.getAttribute( "user" );
        Document document = new Document().find( Integer.parseInt( request.getParameter( "docId" ) ) );

        Comment comment = new Comment().find( Integer.parseInt( request.getParameter( "commentId" ) ) );
        comment.destroy();

        List<Comment> comments = document.getDocumentCommentsByOrder( document.getId() );

        request.setAttribute( "comments", comments );
        request.setAttribute( "document", document );
        request.getRequestDispatcher( "/document" ).forward( request, response );
    }

    protected void commentOnChangeset( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Peer peer = (Peer) session.getAttribute( "user" );
        Document document = new Document().find( Integer.parseInt( request.getParameter( "docId" ) ) );
        Changeset changeset = new Changeset().find( Integer.parseInt( request.getParameter( "changesetId" ) ) );

        Comment comment = new Comment();
        comment.setMessage( request.getParameter( "comment" ).toString() );
        comment.setName( peer.getFirstName() + " " + peer.getLastName() );
        comment.setPeerId( peer.getId() );
        comment.setDocumentId( document.getId() );
        comment.setChangesetId( changeset.getId() );
        comment.save();

        List<Comment> comments = document.getDocumentCommentsByOrder( document.getId() );

        request.setAttribute( "comments", comments );
        request.setAttribute( "document", document );
        request.setAttribute( "changeset", changeset );
        request.getRequestDispatcher( "/document" ).forward( request, response );
    }


}
