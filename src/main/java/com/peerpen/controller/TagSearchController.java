package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;

import java.io.IOException;
import java.net.SocketTimeoutException;
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

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String query = "";
        if (request.getParameter( "tags" )!= null && !request.getParameter( "tags" ).isEmpty()){
            query = request.getParameter( "tags" );
            // converts tags string into a list
            List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );
            // converts list<string> into list<tagdescriptor>
            List<TagDescriptor> tagDescriptors = new TagDescriptor(  ).getTagDescriptors(tagNames);
            // find match
            List<Group> groups = new Group(  ).getMatchedGroups( tagDescriptors );
            List<Document> documents = new ArrayList<>(  );

            session.setAttribute("tagSearchResultsGroups", groups);
            session.setAttribute("tagSearchResultsDocuments", documents);
        }
        response.sendRedirect( "/tag" );
    }
}
