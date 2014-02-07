package com.peerpen.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.model.Avatar;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.json.JsonObject;
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
        String requestType = request.getParameter( "format" );
        // ajax autocomplete request
        if (requestType != null && requestType.equals( "json" )){ // request.getAttribute ("applicationJson") doesnt work
            String q = " ";
            if( request.getParameter("term")!= null){
                q = request.getParameter( "term" );
            }

            String area = "";
            if (request.getParameter( "area" )!= null){
                area = request.getParameter( "area" );
            }

            String json = ""; // json format [{"value":"resue", "desc":"document"}]
            switch(area){
                case "documents":
                    json = getJsonForDocuments( new Document().getSuggestions( q, 5 ) );
                    break;
                case "peers":
                    json = getJsonForPeers( new Peer().getSuggestions( q, 5 ) );
                    break;
                case "groups":
                    json = getJsonForGroups( new Group(  ).getSuggestions( q, 5 ) );
                    break;
                default:
                    json = getJsonForDocuments( new Document().getSuggestions( q, 5 ) );
                    json += getJsonForPeers( new Peer().getSuggestions( q, 5 ) );
                    json += getJsonForGroups( new Group(  ).getSuggestions( q, 5 ) );
            }

            json = "[" + removeTrailingComma( json ) + "]";
            System.out.println(json);

            // Return json string as response
            response.setContentType( "application/json" );
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);


        }else{  // normal post request from jsp


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

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/view/search.jsp").forward(request, response);
    }

    private static String removeTrailingComma(String s){
        if (s.endsWith( "," )){
            s = s.substring( 0, s.lastIndexOf( "," ) );
        }
        return s;
    }

    // below methods are to generate specific json string for each of the searchables
    private static String getJsonForDocuments(List<Document> list){
        String json = "";
        for (Document d : list){
            json += "{\"value\":\"" + d.getDocName() + "\",\"desc\":\"Document\"},";
        }
        return json;
    }

    private static String getJsonForPeers(List<Peer> list){
        String json = "";
        for (Peer p : list){
            json += "{\"value\":\"" + p.getUserName() + "\",\"desc\":\"" + p.getFirstName() + " " + p.getLastName() + "\"},";
        }
        return json;
    }

    private static String getJsonForGroups(List<Group> list){
        String json = "";
        for (Group g : list){
            json += "{\"value\":\"" + g.getGroupName() + "\",\"desc\":\"Group\"},";
        }
        return json;
    }
}
