package com.peerpen.controller;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Integer id = Integer.parseInt( request.getParameter( "entityId" ) ); // id
        String type = request.getParameter( "entityType" ).toLowerCase();
        String query = request.getParameter( "tags" );

        List<String> tagNames = Arrays.asList( query.split( "\\s*,\\s*" ) );
        List<TagDescriptor> newTagDescriptors = new ArrayList<>(  );

        // case where user removes all tags hence query is empty
        if (!query.isEmpty()){
            for (String tagName: tagNames){
                newTagDescriptors.add(new TagDescriptor(  ).getTagDescriptor( tagName ));
            }
        }

        switch ( type ){
            case "group":
                Group group = new Group(  ).find( id );
                group.updateTags( newTagDescriptors );
                break;
            case "document":
                Document document = new Document( ).find( id );
                document.updateTags( newTagDescriptors );
                break;
            default:
                System.out.println("type is not specified");
        }

        response.sendRedirect( request.getHeader( "referer" ) );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
