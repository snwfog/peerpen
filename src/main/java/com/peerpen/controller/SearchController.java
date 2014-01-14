package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.ArrayList;
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
            List<Object> everything = new ArrayList<Object>(  );
            everything.addAll( getMatchedDocuments( query ));
            everything.addAll( getMatchedPeers( query ));
            //everything.addAll( getMatchedGroups( query ));
            session.setAttribute( "searchResults", everything );
        }else if(area.equals( "documents" )){
            session.setAttribute("searchResults", getMatchedDocuments( query ));
        }else if(area.equals( "peers" )){
            session.setAttribute("searchResults", getMatchedPeers( query ));
        }else if(area.equals( "groups" )){
            //session.setAttribute("searchResults", getMatchedGroups( query ));
        }else if(area.equals( "tags" )){
            //session.setAttribute("searchResults", getMatchedTags( query ));
        }
        response.sendRedirect( "/search" );
    }

    private List getMatchedDocuments(String keyword){
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%'";
        List<Document> documents = new Document().queryAll(sql);
        return documents;
    }

    private List getMatchedPeers(String keyword){
        String sql = "SELECT * FROM `peers` WHERE `user_name` LIKE '%" + keyword + "%'";
        List<Peer> peers = new Peer().queryAll(sql);
        return peers;
    }

    private List getMatchedGroups(String keyword){
        String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%'";
        List<Group> groups = new Group().queryAll(sql);
        return groups;
    }

    //private List getMatchedTags(String keyword){
    //    String sql = "SELECT * FROM `tag_descriptors` WHERE `tag_name` LIKE '%" + keyword + "%'";
    //    List<Tag> tags = new Tag().queryAll(sql);
    //    return tags;
    //}
}
