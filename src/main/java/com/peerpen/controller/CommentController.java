package com.peerpen.controller;

import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bobbyyit
 * Date: 2014-01-05
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */


public class CommentController extends HttpServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();

    Peer pear = (Peer)session.getAttribute("user");

//    Need to implement. Need to get comments from the
//    Document document = new Document().find(request.getAttribute("document"));
//    List<Comment> comments = document.getComments();

//    Need to implement setters and getters for docId, peerId etc
//    Comment comment = new Comment();
//    comment.setMessage("This resume is bad, fix it!");
//    comment.setDocumentId(document.getId());
//    comment.save();

//    request.setAttribute("document", document);
//    request.setAttribute("comments", comments);

//    Experimental:
    List<Comment> list = getComments();
    request.setAttribute("comments", list);


    request.getRequestDispatcher("/comment").forward(request, response);

  }

  private List<Comment> getComments()
  {
    List<Comment> list = new ArrayList<Comment>();

    Comment comment1 = new Comment();
    comment1.setMessage("Enlargo Boobidus Expecto!");
    comment1.setName("Hermione Granger");

    Comment comment2 = new Comment();
    comment2.setMessage("Fapon Patronum!!");
    comment2.setName("Harry Potter");

    Comment comment3 = new Comment();
    comment3.setMessage("Erecto Gigantum!");
    comment3.setName("Draco Malfoy");

    list.add(comment1);
    list.add(comment2);
    list.add(comment3);
    return list;
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Peer peer = (Peer)session.getAttribute("user");

    Comment comment = new Comment();
    comment.setMessage(request.getParameter("comment").toString());
    comment.setName(peer.getFirstName() + " " +peer.getLastName());

    List<Comment> list = getComments();
    list.add(comment);

    request.setAttribute("comments", list);
    request.getRequestDispatcher("/comment").forward(request, response);

  }

}
