package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import java.io.IOException;
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
        super.doGet( request, response );
        Map<String, Object> parameters = internalRequest.getParametersMap();
        // FIXME: parameters is not garantee to be clean, need distinguish between parameters and URL parameters
        Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap( parameters );
        Peer sessionUser = (Peer) request.getAttribute( "sessionUser" );
        Peer urlUser = (Peer) modelMap.get( "peer" );
        Document document;
        if ( sessionUser.getId() == urlUser.getId() ) {
            // View documents
            if ( (document = (Document) modelMap.get( "document" )) == null ) {
                request.setAttribute( "peer", modelMap.get( "peer" ) );
                request.getRequestDispatcher( "/view/documents.jsp" ).forward( request, response );
            }
            //        View document
            else {
                if ( document.getPeerId() == sessionUser.getId() ) {
                    request.setAttribute( "urlUser", urlUser );
                    request.setAttribute( "document", modelMap.get( "document" ) );
                    request.getRequestDispatcher( "/view/document.jsp" ).forward( request, response );
                } else {
                    try {
                        throw new PermissionDeniedException( "You have no permission to view this document :/" );
                    } catch ( PermissionDeniedException e ) {
                        ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request,
                                response, e );
                    }
                }
            }
        } else // If viewing someone else doc, show 'view only' mode
        {
            if ( (document = (Document) modelMap.get( "document" )) == null ) {
                request.setAttribute( "peer", modelMap.get( "peer" ) );
                request.getRequestDispatcher( "/view/documents.jsp" ).forward( request, response );
            } else {
                if ( document.getPeerId() == urlUser.getId() ) {
                    request.setAttribute( "document", modelMap.get( "document" ) );
                    request.setAttribute( "urlUser", urlUser );
                    request.getRequestDispatcher( "/view/document.jsp" ).forward( request, response );
                } else {
                    try {
                        throw new PermissionDeniedException( "You have no permission to view this document :/" );
                    } catch ( PermissionDeniedException e ) {
                        ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request,
                                response, e );
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

            String jsonObjectAsString = request.getParameter( "data" );
            doc.commitDocumentFromRawJson( jsonObjectAsString );
            logger.info( "Document updated for " + doc.getId() );
        } catch ( Exception e ) {
            System.out.println( "Problem" );
        }
    }
}