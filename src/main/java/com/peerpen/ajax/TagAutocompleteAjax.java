package com.peerpen.ajax;

import com.google.gson.Gson;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.peerpen.model.TagDescriptor;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/24/2014
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagAutocompleteAjax extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String q = " ";
        if( request.getParameter("term")!= null){
            q = request.getParameter( "term" );
        }

        Set suggestionPool = new LinkedHashSet(  );
        suggestionPool.addAll( new TagDescriptor(  ).getSuggestedTagDescriptors(q, 3) );

        // Convert set into json string
        String json = new Gson().toJson( suggestionPool );

        // Return json string as response
        response.setContentType( "application/json" );
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
