package com.peerpen.controller;

import com.google.gson.Gson;
import com.peerpen.helper.Autocomplete;
import com.peerpen.helper.Search;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/24/2014
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagCloudController extends HttpServlet {

    /**
     * doGet method gets the tagCloud and redirect to tagcloud.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // Get a list of all tag descriptors
        request.setAttribute( "tagCloud", TagDescriptor.getTagCloud() );
        request.getRequestDispatcher( "/view/tagcloud.jsp" ).forward( request, response );
    }

    /**
     * doPost method delegates its work to doAutocomplete or doSearch based on the request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String requestType = request.getParameter( "format" );
        // ajax autocomplete request
        if ( requestType != null &&
                requestType.equals( "json" ) ) { // request.getAttribute ("applicationJson") doesnt work
            doAutocomplete( request, response );
        } else { // normal post request from jsp
            doSearch( request, response );
        }
    }

    /**
     * doAutocomplete returns a json containing a list of suggested tds on each request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private static void doAutocomplete( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String q = " ";
        if ( request.getParameter( "term" ) != null ) {
            q = request.getParameter( "term" );
        }

        Set suggestionPool = new LinkedHashSet();
        suggestionPool.addAll( Autocomplete.getSuggestedTagDescriptors( q, 3 ) );
        // Convert set into json string
        String json = new Gson().toJson( suggestionPool );

        // Return json string as response
        response.setContentType( "application/json" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write( json );
    }

    /**
     * doSearch handles a string list of tds from user, searches and return matching entities to view
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private static void doSearch( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String query = "";
        if ( request.getParameter( "tags" ) != null && !request.getParameter( "tags" ).isEmpty() ) {
            query = request.getParameter( "tags" );
            // converts tags string into a list
            List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );
            // converts list<string> into list<tagdescriptor>
            List<TagDescriptor> tagDescriptors = new TagDescriptor().getTagDescriptors( tagNames );

            // find match
            if ( !tagDescriptors.isEmpty() ) {
                List<Group> groups = Search.getMatchedGroups( tagDescriptors );
                List<Document> documents = Search.getMatchedDocuments( tagDescriptors );
                request.setAttribute( "tagSearchResultsGroups", groups );
                request.setAttribute( "tagSearchResultsDocuments", documents );
            }
        }
        request.getRequestDispatcher( "/view/tagcloud.jsp" ).forward( request, response );
    }
}
