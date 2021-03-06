package com.peerpen.ajax;

import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.model.Comment;
import com.peerpen.model.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 25/01/14
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class VoteControllerAjax extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Document document = new Document().find(Integer.parseInt(parameters.get("docid")));
        Comment comment = new Comment().find(Integer.parseInt(parameters.get("commentid")));
        comment.upVote();

        comment.update();
        String message= comment.getId().toString()+"|"+comment.getUpVote().toString();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Comment comment = new Comment().find(Integer.parseInt(parameters.get("commentid")));
        //comment.setDownVote(comment.getDownVote()+1);
        comment.downVote();
        int commentId = comment.getId()+1;
        String message= commentId+"|"+comment.getDownVote().toString();
        comment.update();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);

    }

}
