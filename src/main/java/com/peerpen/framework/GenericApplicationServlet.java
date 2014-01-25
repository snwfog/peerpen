package com.peerpen.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericApplicationServlet extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( GenericApplicationServlet.class );

    protected InternalHttpServletRequest internalRequest;


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
       internalRequest = (InternalHttpServletRequest)request;
    }

}
