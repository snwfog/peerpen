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
import java.util.Map;

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
      Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
      if (request.getParameter("method") != null)
      {

          if(request.getParameter("method").contentEquals("_upVote"))
          {
              addUpVote(request, response);
          }
          else if (request.getParameter("method").contentEquals("_downVote"))
          {
              addDownVote(request,response);
          }
      }


  }
    protected void addUpVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Document document = new Document().find(Integer.parseInt(parameters.get("docid")));
        Comment comment = new Comment().find(Integer.parseInt(parameters.get("commentid")));

        comment.setUpVote(comment.getUpVote()+1);
        //comment.setDownVote(0);

        comment.update();

        //List<Comment> comments = document.getOrderedComments();
        String message= comment.getId().toString()+"|"+comment.getUpVote().toString();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
    }

    protected void addDownVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Comment comment = new Comment().find(Integer.parseInt(parameters.get("commentid")));
        comment.setDownVote(comment.getDownVote()+1);
        int commentId = comment.getId()+1;
        String message= commentId+"|"+comment.getDownVote().toString();
        comment.update();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);

    }


}
