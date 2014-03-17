package com.peerpen.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentController extends GenericApplicationServlet {

    static final Logger logger = LoggerFactory.getLogger( DocumentController.class );

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // FIXME: This is bad, but okay for now I guess... - snw
        // FIXME: And I totally forgot what is the purpose of this...
        super.doGet( request, response );

        Map<String, Object> parameterMap = (Map<String, Object>) request.getAttribute( "parameters" );
        Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap( parameterMap );
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Peer urlUser = (Peer) modelMap.get( "peer" );
        Document urlDocument = (Document) modelMap.get( "document" );
        if ( ((InternalHttpServletRequest) request).isAjaxRequest() ) {
            // Handle ajax stuff...
            System.out.println( "We are loading document via ajax" );
            Gson gson =
                    new GsonBuilder().registerTypeAdapter( Document.class, new Document.DocumentSerializer() ).create();
            PrintWriter pw = response.getWriter();
            String content = "";
            try {
                content = gson.toJson( urlDocument );
                response.addHeader( "Content-Type", "application/json" );
                response.setStatus( 200 );
            } catch ( Exception e ) {
                response.setStatus( 404 );
            } finally {
                pw.print( content );
            }
        } else {
            Map<String, Object> parameters = internalRequest.getParametersMap();
            // FIXME: parameters is not garantee to be clean, need distinguish between parameters and URL parameters
            if ( sessionUser.getId() == urlUser.getId() ) {
                // View documents
                if ( urlDocument == null ) {
                    request.setAttribute( "peer", modelMap.get( "peer" ) );
                    request.getRequestDispatcher( "/view/documents.jsp" ).forward( request, response );
                }
                //        View document
                else {
                    if ( urlDocument.getPeerId() == sessionUser.getId() ) {
                        request.setAttribute( "urlUser", urlUser );
                        request.setAttribute( "document", modelMap.get( "document" ) );
                        request.getRequestDispatcher( "/view/document.jsp" ).forward( request, response );
                    } else {
                        try {
                            throw new PermissionDeniedException( "You have no permission to view this document :/" );
                        } catch ( PermissionDeniedException e ) {
                            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(
                                    request, response, e );
                        }
                    }
                }
            } else // If viewing someone else doc, show 'view only' mode
            {
                if ( urlDocument == null ) {
                    request.setAttribute( "peer", modelMap.get( "peer" ) );
                    request.getRequestDispatcher( "/view/documents.jsp" ).forward( request, response );
                } else {
                    if ( urlDocument.getPeerId() == urlUser.getId() ) {
                        request.setAttribute( "document", modelMap.get( "document" ) );
                        request.setAttribute( "urlUser", urlUser );
                        request.getRequestDispatcher( "/view/document.jsp" ).forward( request, response );
                    } else {
                        try {
                            throw new PermissionDeniedException( "You have no permission to view this document :/" );
                        } catch ( PermissionDeniedException e ) {
                            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(
                                    request, response, e );
                        }
                    }
                }
            }

        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            int documentId = 2;
            Document doc = new Document().find( documentId );
            if ( doc == null ) {
                throw new Exception();
            }

            String jsonObjectAsString = (String) request.getAttribute( "requestData" );
            doc.commitDocumentFromRawJson( jsonObjectAsString );
            logger.info( "Document updated for " + doc.getId() );
        } catch ( Exception e ) {
            System.out.println( "Problem" );
        }
    }
}