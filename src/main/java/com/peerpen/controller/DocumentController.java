package com.peerpen.controller;

import com.google.common.collect.Maps;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.model.Changeset;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentController extends HttpServlet
{

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    Peer owner = (Peer)request.getAttribute("sessionUser");
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peer")));
    Document document = null;

    try
    {
      if((document = peer.getDocument(Integer.parseInt(parameters.get("document.do")))) != null)
      {
        List<Comment> comments = document.getOrderedComments();

        request.setAttribute("document", document);
        request.getRequestDispatcher("/document").forward(request, response);
      }
    }
    catch (PermissionDeniedException e)
    {
      ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(request, response, e);
    }


  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    if (parameters.get("method") != null)
    {
      if (parameters.get("method").contentEquals("_delete"))
      {
        doDelete(request, response);
      }
      else if (parameters.get("method").contentEquals("_doPut"))
      {
        doPut(request, response);
      }
    }
    else
    {

      Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
      Document document = new Document().find(Integer.parseInt(parameters.get("docid")));

      Map<String, Object> map = Maps.newHashMap();
      map.put("message", parameters.get("comment"));
      map.put("peerId", peer.getId());
      map.put("documentId", document.getId());
//      TODO: fix!
      map.put("downVote", 0);
      map.put("upVote", 0);

      Comment comment = new Comment(map);
      comment.save();

      List<Comment> comments = document.getOrderedComments();

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
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");

    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Document document = new Document().find(Integer.parseInt(request.getParameter("docid")));

    Comment comment = new Comment().find(Integer.parseInt(request.getParameter("commentid")));
    comment.destroy();

    List<Comment> comments = document.getOrderedComments();

    request.setAttribute("comments", comments);
    request.setAttribute("document", document);
    request.setAttribute("user", peer);
    response.sendRedirect("peer/2/document.do/1");
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Document document = new Document().find(Integer.parseInt(parameters.get("docid")));
    Changeset changeset = new Changeset().find(Integer.parseInt(parameters.get("changesetid")));

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
    //request.getRequestDispatcher("/document").forward(request, response);
    response.sendRedirect("peer/2/document.do/1");
  }

}
