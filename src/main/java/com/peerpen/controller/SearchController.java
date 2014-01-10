package com.peerpen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.sunnyd.models.*;
import com.sunnyd.database.Manager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

        System.out.println(query + " " + origin + " " + area);

        // Build Search Conditions
        Map<String, Object> conditions = new HashMap<String, Object>(  );
        conditions.put("firstName", "test");

        // Find
        ArrayList results = Manager.findAll( "peers", conditions );
        System.out.println(results.toString());






    }
}
