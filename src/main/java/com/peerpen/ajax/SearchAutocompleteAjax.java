package com.peerpen.ajax;

import com.google.gson.Gson;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/22/2014
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */

public class SearchAutocompleteAjax extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        // http://localhost:8080/search_autocomplete_ajax.do?term=resu

        String q = " ";
        if( request.getParameter("term")!= null){
            q = request.getParameter( "term" );
        }

        String area = "";
        if (request.getParameter( "area" )!= null){
            area = request.getParameter( "area" );
        }


        // Merge all lists into a set (unique)
        Set suggestionPool = new LinkedHashSet(  );

        // Obtains for each searchable model a list of suggestion base on the query
        if(area.equals( "documents" )){
            suggestionPool.addAll( new Document().getSuggestedDocuments( q, 5 ) );
        }else if( area.equals("peers")){
            suggestionPool.addAll( new Peer().getSuggestedPeers( q, 5 ) );
        }else {
            suggestionPool.addAll( new Document().getSuggestedDocuments( q, 3 ) );
            suggestionPool.addAll( new Peer().getSuggestedPeers( q, 3 ) );
        }


        // Convert set into json string
        String json = new Gson().toJson( suggestionPool );

        // Return json string as response
        response.setContentType( "application/json" );
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
