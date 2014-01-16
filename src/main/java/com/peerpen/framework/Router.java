package com.peerpen.framework;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router extends HttpServlet {

    private boolean isPermissibleRoutes(String stringQuery)
    {

        return false;
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) {
        String rURI = request.getRequestURI();

    }


    protected void doPost( HttpServletRequest request, HttpServletResponse response ) {

    }
}
