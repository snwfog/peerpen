package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/13/2014
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagController extends HttpServlet {

    /**
     * doPost method updates the tags associated with a given taggable entity (ie: group)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // Client data handling
        Integer id = Integer.parseInt( request.getParameter( "entityId" ) );
        String type = request.getParameter( "entityType" ).toLowerCase();
        String query = request.getParameter( "tags" );

        // Processing
        List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );
        List<TagDescriptor> newTagDescriptors = new ArrayList<>();

        if ( !query.isEmpty() ) {
            for ( String tagName : tagNames ) {
                newTagDescriptors.add( new TagDescriptor().getTagDescriptor( tagName ) );
            }
        }

        // alternatively use reflection but it is slow
        switch ( type ) {
            case "group":
                Group group = new Group().find( id );
                group.updateTags( newTagDescriptors );
                break;
            case "document":
                Document document = new Document().find( id );
                document.updateTags( newTagDescriptors );
                break;
            default:
                System.out.println( "type is not specified" );
        }

        response.sendRedirect( request.getHeader( "referer" ) );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }
}
