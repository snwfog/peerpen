package com.peerpen.controller;

import com.google.common.collect.Maps;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.peerpen.model.Changeset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CommentController extends HttpServlet
{

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Document document = new Document().find(Integer.parseInt(parameters.get("docid")));

    Map<String, Object> map = Maps.newHashMap();
    map.put("message", parameters.get("comment"));
    map.put("peerId", peer.getId());
    map.put("documentId", document.getId());

    Comment comment = new Comment(map);
    comment.save();

    request.setAttribute("document", document);
    response.sendRedirect(request.getHeader("referer"));
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Document document = new Document().find(Integer.parseInt(parameters.get("docid")));
    Changeset changeset = new Changeset().find(Integer.parseInt(parameters.get("changesetid")));

    Map<String, Object> map = Maps.newHashMap();
    map.put("message", request.getParameter("comment").toString());
    map.put("name", peer.getFirstName() + " " + peer.getLastName());
    map.put("peerId", peer.getId());
    map.put("documentId", document.getId());
    map.put("changesetId", changeset.getId());
    Comment comment = new Comment(map);
    comment.save();

    request.setAttribute("document", document);
    response.sendRedirect(request.getHeader("referer"));
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");

    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Document document = new Document().find(Integer.parseInt(request.getParameter("docid")));

    Comment comment = new Comment().find(Integer.parseInt(request.getParameter("commentid")));
    comment.destroy();

    request.setAttribute("document", document);
    request.setAttribute("user", peer);
    response.sendRedirect(request.getHeader("referer"));
  }
}
