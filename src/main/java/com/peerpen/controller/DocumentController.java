package com.peerpen.controller;

import com.peerpen.model.Changeset;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DocumentController extends HttpServlet
{

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {

    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    System.out.println("Request parameters:" + parameters);
    HttpSession session = request.getSession();
//        Peer peer = (Peer) session.getAttribute( "user" );
//    Temp fix.. session above returns null
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peer")));
    session.setAttribute("user", peer);

    Document document = new Document().find(Integer.parseInt(parameters.get("document.do")));

    List<Comment> comments = document.getOrderedComments();
//    List<Changeset> changesets = document.getChangesets();


    request.setAttribute("comments", comments);
    request.setAttribute("document", document);
    request.getRequestDispatcher("/document").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    if (request.getParameter("_method") != null)
    {
      if (request.getParameter("_method").contentEquals("_delete"))
      {
        doDelete(request, response);
      }
      else if (request.getParameter("_method").contentEquals("_doPut"))
      {
        doPut(request, response);
      }
    }
    else
    {
      HttpSession session = request.getSession();
      Peer peer = (Peer) session.getAttribute("user");
      Document document = new Document().find(Integer.parseInt(request.getParameter("docId")));


      Comment comment = new Comment();
      comment.setMessage(request.getParameter("comment").toString());
      comment.setName(peer.getFirstName() + " " + peer.getLastName());
      comment.setPeerId(peer.getId());
      comment.setDocumentId(document.getId());
      comment.setUpVote(0);
      comment.setDownVote(0);

      comment.save();

      List<Comment> comments = document.getOrderedComments();

      request.setAttribute("comments", comments);
      request.setAttribute("document", document);
      request.getRequestDispatcher("/document").forward(request, response);
    }
  }

  /*
   * Never called explicitly
   */
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Peer peer = (Peer) session.getAttribute("user");
    Document document = new Document().find(Integer.parseInt(request.getParameter("docId")));

    Comment comment = new Comment().find(Integer.parseInt(request.getParameter("commentId")));
    comment.destroy();

    List<Comment> comments = document.getOrderedComments();

    request.setAttribute("comments", comments);
    request.setAttribute("document", document);
    request.getRequestDispatcher("/document").forward(request, response);
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Peer peer = (Peer) session.getAttribute("user");
    Document document = new Document().find(Integer.parseInt(request.getParameter("docId")));
    Changeset changeset = new Changeset().find(Integer.parseInt(request.getParameter("changesetId")));

    Comment comment = new Comment();
    comment.setMessage(request.getParameter("comment").toString());
    comment.setName(peer.getFirstName() + " " + peer.getLastName());
    comment.setPeerId(peer.getId());
    comment.setDocumentId(document.getId());
    comment.setChangesetId(changeset.getId());
    comment.setUpVote(0);
    comment.setDownVote(0);
    comment.save();

    List<Comment> comments = document.getOrderedComments();

    request.setAttribute("comments", comments);
    request.setAttribute("document", document);
    request.setAttribute("changeset", changeset);
    request.getRequestDispatcher("/document").forward(request, response);
  }

}
