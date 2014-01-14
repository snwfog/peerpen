package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/9/2014
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String query = request.getParameter( "query" );
        String area = request.getParameter( "area" ); // area of search

        String origin = request.getRequestURI();

        HttpSession session = request.getSession();
        session.setAttribute( "searchArea", area );

        //System.out.println(query + " " + origin + " " + area);


        if(area.equals( "all" )){
            //
        }else if(area.equals( "documents" )){
            String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + query + "%'";
            List<Document> documents = new Document().queryAll(sql);
            session.setAttribute("searchResults", documents);
        }else if(area.equals( "peers" )){
            String sql = "SELECT * FROM `peers` WHERE `user_name` LIKE '%" + query + "%'";
            List<Peer> peers = new Peer().queryAll(sql);
            session.setAttribute("searchResults", peers);
        }else if(area.equals( "groups" )){
            String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + query + "%'";
            List<Group> groups = new Group().queryAll(sql);
            session.setAttribute("searchResults", groups);
        }else if(area.equals( "tags" )){
            String sql = "SELECT * FROM `tag_descriptors` WHERE `tag_name` LIKE '%" + query + "%'";
            //List<Tag> tags = new Tag().queryAll(sql);
            //session.setAttribute("searchResults", tags);
        }
        response.sendRedirect( "/search" );
    }
}
