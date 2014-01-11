package com.peerpen.controller;

import com.peerpen.model.Changeset;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 10/01/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Peer pear = (Peer)session.getAttribute("user");

        int doc_id = Integer.parseInt(request.getParameter("doc_id"));

        Document document = new Document().find(doc_id);
        //Document document = new Document();//.find(request.getAttribute("document"));
        //List<Comment> comments = document.getComments();
        /*List<Changeset> sc = document.getChangesets();
        for (Changeset changeset : sc){
            List<Comment> comment =  changeset.getComments();
        }
        */
//    List<Comment> comments = document.getComments();

//    Need to implement setters and getters for docId, peerId etc
//    Comment comment = new Comment();
//    comment.setMessage("This resume is bad, fix it!");
//    comment.setDocumentId(document.getId());
//    comment.save();

//    request.setAttribute("document", document);
//    request.setAttribute("comments", comments);

//    Experimental:
        //List<Document> documents = pear.getDocuments();


        System.out.println("dsfsdfdsfdsfdfd"+document.getId());

        List<Comment> list = document.getComments();
        request.setAttribute("comments", list);

        request.setAttribute("document", document);

        request.getRequestDispatcher("/document").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Peer peer = (Peer)session.getAttribute("user");
        //Document document = (Document)request.getAttribute("document");

        int doc_id = Integer.parseInt(request.getParameter("doc_id"));

        Document document = new Document().find(doc_id);

        Comment comment = new Comment();
        comment.setMessage(request.getParameter("comment").toString());
        comment.setName(peer.getFirstName() + " " +peer.getLastName());
        comment.setPeerId(peer.getId());
        comment.setDocumentId(document.getId());
        comment.save();
        //comment.save();


        List<Comment> comments = document.getComments();

        comments.add(comment);

        document.update();
        request.setAttribute("comments", comments);
        request.setAttribute("document",document);
        //request.setAttribute("comments", comments);
        request.getRequestDispatcher("/document").forward(request, response);
    }

}
