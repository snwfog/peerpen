package com.peerpen.controller;

import com.google.gson.Gson;
import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/17/2014
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutocompleteController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String query = request.getParameter( "query" );
        System.out.print( "user input:" + query );

        if (query.isEmpty()){
            query = " ";
        }

        List<String> documents = new Document().getSuggestedDocuments( query, 1 );
        List<String> peers = new Peer().getSuggestedPeers( query, 1 );

        Set suggestionPool = new LinkedHashSet(  );
        suggestionPool.addAll( documents );
        suggestionPool.addAll( peers );

        String json = new Gson().toJson( suggestionPool );
        System.out.print( "returned suggestions:" + json );

        response.setContentType( "application/json" );
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doPost( request, response );
    }
}
