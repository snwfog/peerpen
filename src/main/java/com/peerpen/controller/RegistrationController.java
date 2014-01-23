package com.peerpen.controller;

import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.RegistrationFailedException;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( RegistrationController.class );

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
            internalRequest.expectPresenceOf( "first_name", "last_name", "user_name", "email", "password" );
            Map<String, Object> map = (Map<String, Object>) request.getAttribute( "parameters" );
            Peer p = new Peer( map );

            if ( (map.get( "password" )).equals( map.get( "confirmPassword" ) ) && p.save() ) {
                internalRequest.getRequestDispatcher( "/peer/" + p.getId() + "/profile" ).forward( request, response );
            } else {
                throw new RegistrationFailedException( map );
            }

        } catch ( MissingArgumentException | OperationNotSupportedException | RegistrationFailedException e ) {
            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response, e );
        }
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.getRequestDispatcher( "/view/register.jsp" ).forward( request, response );
    }
}
