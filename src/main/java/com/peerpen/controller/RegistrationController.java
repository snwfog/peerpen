package com.peerpen.controller;

import com.google.common.collect.ImmutableMap;
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
            internalRequest.expectPresenceOf( "first_name", "last_name", "user_name", "email", "password", "confirm_password");
            Map<String, Object> map = (Map<String, Object>) request.getAttribute( "parameters" );
            Peer peer = (new Peer()).find( ImmutableMap.of( "userName", map.get( "userName" ) ) );
            if ( peer != null ) {
                throw new RegistrationFailedException( "User already exists " + map.get( "userName" ) );
            }
            Peer p = new Peer( map );
            p.setCompleteProfile(0);
            // TODO: Validate uniqueness either using Java, or database constraint
            // Create the user unquestionably given that this user does not exists already
            if ( (map.get( "password" )).equals( map.get( "confirmPassword" ) )&& p.save()) {
                p.setSessionId( request.getSession().getId() );
                p.update();
                response.sendRedirect("/peer/" + p.getId() + "/profile");
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
