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
        String query = " "; // prevent wild search
        String area = "";

        if (request.getParameter( "area" )!= null){
            area = request.getParameter( "area" );
        }
        if (request.getParameter( "query" )!= null && !request.getParameter( "query" ).isEmpty()){
            query = request.getParameter( "query" );
        }

        String origin = request.getRequestURI();
        HttpSession session = request.getSession();

        if(area.equals( "documents" )){
            session.setAttribute("searchResults", new Document().getMatchedDocuments( query ));
        }else if(area.equals( "peers" )){
            session.setAttribute("searchResults", new Peer().getMatchedPeers( query ));
        }else if(area.equals( "groups" )){
            session.setAttribute("searchResults", new Group().getMatchedGroups( query ));
        }else if(area.equals( "tags" )){
            //session.setAttribute("searchResults", getMatchedTags( query ));
        }else{ // all or not set
            List<Object> everything = new ArrayList<Object>(  );
            everything.addAll( new Document().getMatchedDocuments( query ));
            everything.addAll( new Peer().getMatchedPeers( query ));
            everything.addAll( new Group().getMatchedGroups( query ));
            session.setAttribute( "searchResults", everything );
        }

        response.sendRedirect( "/search" );
    }
}
