package com.peerpen.controller;

import com.google.gson.Gson;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/24/2014
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagSearchController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String requestType = request.getParameter( "format" );
        // ajax autocomplete request
        if (requestType != null && requestType.equals( "json" )){ // request.getAttribute ("applicationJson") doesnt work
            doAutocomplete( request, response );
        }else{ // normal post request from jsp
            doSearch( request, response );
        }
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Get a list of all tag descriptors
        request.setAttribute( "tagCloud", TagDescriptor.getTagCloud() );
        request.getRequestDispatcher("/view/tagsearch.jsp").forward(request, response);
    }

    private static void doAutocomplete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int numOfSuggestions = 3;
        String q = " ";
        if( request.getParameter("term")!= null){
            q = request.getParameter( "term" );
        }

        Set suggestionPool = new LinkedHashSet(  );
        suggestionPool.addAll( new TagDescriptor(  ).getSuggestedTagDescriptors(q, numOfSuggestions) );
        // Convert set into json string
        String json = new Gson().toJson( suggestionPool );

        // Return json string as response
        response.setContentType( "application/json" );
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private static void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = "";
        if (request.getParameter( "tags" )!= null && !request.getParameter( "tags" ).isEmpty()){
            query = request.getParameter( "tags" );
            // converts tags string into a list
            List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );
            // converts list<string> into list<tagdescriptor>
            List<TagDescriptor> tagDescriptors = new TagDescriptor(  ).getTagDescriptors(tagNames);

            // find match
            if(!tagDescriptors.isEmpty()){
                List<Group> groups = new Group(  ).getMatchedGroups( tagDescriptors );
                List<Document> documents = new Document(  ).getMatchedDocuments( tagDescriptors );
                request.setAttribute( "tagSearchResultsGroups", groups );
                request.setAttribute( "tagSearchResultsDocuments", documents );
            }
        }
        request.getRequestDispatcher( "/view/tagsearch.jsp" ).forward( request, response );
        //response.sendRedirect( request.getHeader( "referer" ) );

    }
}
