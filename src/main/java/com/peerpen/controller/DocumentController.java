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
        Document document = new Document().find(Integer.parseInt(request.getParameter("doc_id")));


//        List<Object> o = method to get changeset AND comments already sorted out by points

        List<Comment> list = document.getComments();
        System.out.println("111111111111111111111"+list);

        List<Comment> objects = document.getDocumentCommentsByOrder();

        System.out.println("111111111111111111111"+objects);
        for(Comment l:objects)
        {
            System.out.println("222222222222"+l);
            System.out.println("22222222222222"+l.getClass());
            System.out.println("22222222222222"+l.getClass().getDeclaredClasses());
            System.out.println("22222222222222"+l.getClass().getAnnotations());
            System.out.println("22222222222222"+l.getClass().getDeclaringClass());
        }
        request.setAttribute("comments", list);
        request.setAttribute("document", document);
        request.getRequestDispatcher("/document").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      HttpSession session = request.getSession();
      Peer peer = (Peer)session.getAttribute("user");
      Document document = new Document().find(Integer.parseInt(request.getParameter("doc_id")));

      if (request.getParameter("method")!= null)
      {
        if(request.getParameter("method").contentEquals("DELETE"))
        {
         Comment comment =  new Comment().find(Integer.parseInt(request.getParameter("commentId")));
         comment.destroy();
        }
      }
      else
      {
        Comment comment = new Comment();
        comment.setMessage(request.getParameter("comment").toString());
        comment.setName(peer.getFirstName() + " " +peer.getLastName());
        comment.setPeerId(peer.getId());
        comment.setDocumentId(document.getId());
        comment.save();
      }

      List<Comment> comments = document.getComments();

      request.setAttribute("comments", comments);
      request.setAttribute("document", document);
      request.getRequestDispatcher("/document").forward(request, response);
    }

}
