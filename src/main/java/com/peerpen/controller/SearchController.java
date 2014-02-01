package com.peerpen.controller;

import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.MissingArgumentException;
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
        String query = "";
        String area = "";

        if (request.getParameter( "area" )!= null){
            area = request.getParameter( "area" );
        }

        if (request.getParameter( "search_query" )!= null && !request.getParameter( "search_query" ).isEmpty()){
            query = request.getParameter( "search_query" );
        }

        //String origin = request.getRequestURI();
        HttpSession session = request.getSession();

        if (!query.isEmpty()){
            switch (area){
                case "documents":
                    session.setAttribute("searchResults", new Document().getMatchedDocuments( query ));
                    break;
                case "peers":
                    session.setAttribute("searchResults", new Peer().getMatchedPeers( query ));
                    break;
                case "groups":
                    session.setAttribute("searchResults", new Group().getMatchedGroups( query ));
                    break;
                default:
                    List<Object> everything = new ArrayList<Object>(  );
                    everything.addAll( new Document().getMatchedDocuments( query ));
                    everything.addAll( new Peer().getMatchedPeers( query ));
                    everything.addAll( new Group().getMatchedGroups( query ));
                    session.setAttribute( "searchResults", everything );
            }
        }
        response.sendRedirect( "/search" );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/view/search.jsp").forward(request, response);
    }
}
