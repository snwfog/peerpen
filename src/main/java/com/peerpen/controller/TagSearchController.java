package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        String query = "";
        if (request.getParameter( "tag_query" )!= null && !request.getParameter( "tag_query" ).isEmpty()){
            query = request.getParameter( "tag_query" );
        }
        List<String> tags = Arrays.asList( query.split( "\\s*,\\s*" ) );

        HttpSession session = request.getSession();
        //session.setAttribute("tagSearchResults", new Taggable().getMatchedTaggables( tags ));

        for(int i=0;i<tags.size();i++){
            System.out.println(tags.get( i ).toString() + " + ");
        }

    }
}
