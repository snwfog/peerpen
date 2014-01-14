package com.peerpen.controller;

import com.sunnyd.database.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    private static ArrayList results = new ArrayList(  );

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String query = request.getParameter("q");
        System.out.println("here--->" + query);

        List<String> countries = TagController.getData( query );

        Iterator<String> iterator = countries.iterator();
        while(iterator.hasNext()) {
            String country = (String)iterator.next();
            System.out.println(country);
        }


        //
        //Map<String, Object> conditions = new HashMap<String, Object>(  );
        //results = Manager.findAll( "tags", conditions );
        //System.out.println("results:" + results);
    }

    public static List<String> getData(String query) {
        String tag = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<results.size(); i++) {
            tag = results.get(i).toString().toLowerCase();
            if(tag.startsWith(query)) {
                matched.add( results.get( i ).toString() );
            }
        }
        return matched;
    }
}
