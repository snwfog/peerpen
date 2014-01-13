package com.peerpen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.sunnyd.models.*;
import com.sunnyd.database.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String query = request.getParameter( "query" );
        String area = request.getParameter( "area" ); // area of search
        String origin = request.getRequestURI();

        Map<String, Object> conditions = new HashMap<String, Object>(  );
        ArrayList results = new ArrayList(  );


        //System.out.println(query + " " + origin + " " + area);

        //
        if(area.equals( "all" )){
            //
        }else if(area.equals( "documents" )){
            conditions.put("docName", query);
            results = Manager.findAll( "documents", conditions );
        }else if(area.equals( "peers" )){
            conditions.put("userName", query);
            results = Manager.findAll( "peers", conditions );
        }else if(area.equals( "groups" )){
            conditions.put("groupName", query);
            results = Manager.findAll( "groups", conditions );
        }else if(area.equals( "tags" )){
            //
        }

        //System.out.println("result:" + results.toString());

        // Forward results
        HttpSession session = request.getSession();
        session.setAttribute("searchResults", results);
        response.sendRedirect( "/search" );
    }
}
