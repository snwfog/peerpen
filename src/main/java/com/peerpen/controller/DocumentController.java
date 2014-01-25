package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
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

        //if ( parameters.get( "document.do" ) == null ) {
        //    request.setAttribute( "peer", peer );
        //    request.getRequestDispatcher( "/documents" ).forward( request, response );
        //} else {
        //    try {
        //        if ( (document = peer.getDocument( Integer.parseInt( parameters.get( "document.do" ) ) )) != null ) {
        //            request.setAttribute( "document", document );
        //            request.getRequestDispatcher( "/document" ).forward( request, response );
        //        }
        //    } catch ( PermissionDeniedException e ) {
        //        ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response,
        //                e );
        //    }
        //}
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }

}