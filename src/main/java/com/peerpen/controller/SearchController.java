package com.peerpen.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.helper.Autocomplete;
import com.peerpen.helper.Search;
import com.peerpen.model.Avatar;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;
import com.peerpen.model.TagDescriptor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
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

    /**
     * doGet method simply forwards the request to search.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/view/search.jsp").forward(request, response);
    }

    /**
     * doPost delegates its work to either doAutocomplete or doSearch based on the request source
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String requestType = request.getParameter( "format" );
        // ajax autocomplete request
        if (requestType != null && requestType.equals( "json" )){ // request.getAttribute ("applicationJson") doesnt work
            doAutocomplete( request, response );
        }else{  // normal post request from jsp
            doSearch( request, response );
        }
    }

    /**
     * doAutocomplete returns a json containing a list of suggested entity to the js so it can fill the suggestion dropdown box
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private static void doAutocomplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
                json = Autocomplete.getSuggestedDocuments( q, 5 );
                break;
            case "peers":
                json = Autocomplete.getSuggestedPeers( q, 5 ); //getJsonForPeers( new Peer().getSuggestions( q, 5 ) );
                break;
            case "groups":
                json = Autocomplete.getSuggestedGroups( q, 5 );
                break;
            default:
                json = Autocomplete.getSuggestedDocuments( q, 5 );
                json += Autocomplete.getSuggestedPeers( q, 5 );
                json += Autocomplete.getSuggestedGroups( q, 5 );
        }
        // remove trailling comma
        if (json.endsWith( "," )){
            json = json.substring( 0, json.lastIndexOf( "," ) );
        }
        json = "[" + json + "]";

        // Return json string as response
        response.setContentType( "application/json" );
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }


    /**
     * doSearch returns matching entities as a list to the view
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private static void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean doTagMatching = false;
        String query = "";
        String area = "";
        List<String> tagNames;
        List<TagDescriptor> tagDescriptors = null;

        if (request.getParameter( "area" )!= null){
            area = request.getParameter( "area" );
        }

        if (request.getParameter( "search_query" )!= null && !request.getParameter( "search_query" ).isEmpty()){
            query = request.getParameter( "search_query" );
        }

        if (request.getParameter( "searchTags" ) != null){
            if (request.getParameter( "searchTags" ).equals( "on" )){
                doTagMatching = true;
            };
        }

        if (doTagMatching) {
            tagNames = Arrays.asList( query.split( "\\s* \\s*" ) );
            tagDescriptors = new TagDescriptor(  ).getTagDescriptors(tagNames);
        }

        if (!query.isEmpty()){
            switch (area){
                case "documents":
                    List<Document> documents = new ArrayList<>(  );
                    documents.addAll( Search.getMatchedDocuments( query ) );
                    if (doTagMatching && tagDescriptors.size() > 0) {
                        documents.addAll( Search.getMatchedDocuments( tagDescriptors ) );
                    }
                    request.setAttribute( "searchResults", documents );
                    break;
                case "peers":
                    request.setAttribute( "searchResults", Search.getMatchedPeers( query ) );
                    break;
                case "groups":
                    List<Group> groups = new ArrayList<>(  );
                    groups.addAll( Search.getMatchedGroups( query ) );
                    if (doTagMatching && tagDescriptors.size() > 0) {
                        groups.addAll( Search.getMatchedGroups( tagDescriptors ) );
                    }
                    request.setAttribute( "searchResults", groups );
                    break;
                default:
                    List<Object> everything = new ArrayList<Object>(  );
                    everything.addAll( Search.getMatchedDocuments( query ) );
                    everything.addAll( Search.getMatchedPeers( query ) );
                    everything.addAll( Search.getMatchedGroups( query ) );
                    if (doTagMatching && tagDescriptors.size() > 0) {
                        everything.addAll( Search.getMatchedDocuments( tagDescriptors ) );
                        everything.addAll( Search.getMatchedGroups( tagDescriptors ) );
                    }
                    request.setAttribute( "searchResults", everything );
            }
        }

        request.getRequestDispatcher("/view/search.jsp").forward( request, response );
    }
}
