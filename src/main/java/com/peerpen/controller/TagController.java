package com.peerpen.controller;
import com.peerpen.model.TagDescriptor;

import java.io.IOException;
import java.util.ArrayList;
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
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Get a list of all tag descriptors
        Map<String, Object> map = new HashMap<String, Object>();
        List<TagDescriptor> tagDescriptors = new TagDescriptor().findAll( map );
        request.setAttribute( "tagCloud", tagDescriptors );
        request.getRequestDispatcher("/tag").forward(request, response);
    }
}
