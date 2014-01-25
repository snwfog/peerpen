package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.ModelHierarchyUtil;
import com.sunnyd.Base;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentController extends GenericApplicationServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // FIXME: This is bad, but okay for now I guess... - snw
        super.doGet( request, response );
        Map<String, Object> parameters = internalRequest.getParametersMap();
        // FIXME: parameters is not garantee to be clean, need distinguish between parameters and URL parameters
        Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap( parameters );

        if ( modelMap.get( "document" ) == null ) {
            // Return all documents
            request.setAttribute( "peer", modelMap.get( "peer" ) );
            request.getRequestDispatcher( "/view/documents.jsp" ).forward( request, response );

            // TODO: Need to handle better the display of single and multiple documents
        } else {
            request.setAttribute( "document", modelMap.get( "document" ) );
            request.getRequestDispatcher( "/view/document.jsp" ).forward( request, response );
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }

}