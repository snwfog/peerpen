package com.peerpen.controller;

import com.peerpen.model.Comment;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.peerpen.model.Changeset;

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

  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      if (request.getParameter("_method") != null)
      {

          if(request.getParameter("_method").contentEquals("_upVote"))
          {
              addUpVote(request, response);
          }
          else if (request.getParameter("_method").contentEquals("_downVote"))
          {
              addDownVote(request,response);
          }
      }


  }
    protected void addUpVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Peer peer = (Peer)session.getAttribute("user");
        Document document = new Document().find(Integer.parseInt(request.getParameter("docId")));
        Comment comment = new Comment().find(Integer.parseInt(request.getParameter("commentId")));

        comment.setUpVote(comment.getUpVote()+1);
        //comment.setDownVote(0);

        comment.update();

        List<Comment> comments = document.getOrderedComments();

        request.setAttribute("comments", comments);
        request.setAttribute("document", document);
        request.setAttribute("comment", comment);
        request.getRequestDispatcher("/comment").forward(request, response);
    }

    protected void addDownVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Peer peer = (Peer)session.getAttribute("user");
        Document document = new Document().find(Integer.parseInt(request.getParameter("docId")));
        Comment comment = new Comment().find(Integer.parseInt(request.getParameter("commentId")));

        comment.setDownVote(comment.getDownVote()+1);
        //comment.setDownVote(0);

        comment.update();

        List<Comment> comments = document.getOrderedComments();

        request.setAttribute("comments", comments);
        request.setAttribute("document", document);
        request.setAttribute("comment", comment);
        request.getRequestDispatcher("/comment").forward(request, response);
    }


}
