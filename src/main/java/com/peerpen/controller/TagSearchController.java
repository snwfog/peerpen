package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String query = "";
        if (request.getParameter( "tag_query" )!= null && !request.getParameter( "tag_query" ).isEmpty()){
            query = request.getParameter( "tag_query" );
        }
        List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );

        List<Group> groups = new ArrayList<>(  );
        List<Document> documents = new ArrayList<>(  );

        HttpSession session = request.getSession();
        // for each tagname, get all related entities
        for (int i=0;i<tagNames.size();i++){
            TagDescriptor td = new TagDescriptor(  ).getTagDescriptor( tagNames.get( i ) );
            groups.addAll( new Taggable(  ).getMatchedGroups( td ) );
            documents.addAll( new Taggable().getMatchedDocuments( td ) );
        }
        session.setAttribute("tagSearchResultsGroups", groups);
        session.setAttribute("tagSearchResults", documents);

        // to be solved: make list unique

        response.sendRedirect( "/tag" );
    }
}
