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

public class RegistrationController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        final String FORM_REGISTER = "register";
        final String FORM_INDEX = "index";
        Map<String, Object> map = (Map<String, Object>) request.getAttribute( "parameters" );
        String form = map.get( "form" ).toString();

        try {
            InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
            if(form.equals(FORM_REGISTER))
               internalRequest.expectPresenceOf( "first_name", "last_name", "user_name", "email", "password",
                    "confirm_password" );
            else
                internalRequest.expectPresenceOf( "user_name", "email", "password");
            Peer peer = (new Peer()).find( ImmutableMap.of( "userName", map.get( "userName" ) ) );
            if ( peer != null ) {
                throw new RegistrationFailedException( "User already exists " + map.get( "userName" ) );
            }
            Peer p = new Peer( map );
            p.setCompleteProfile( 0 );
            // Create the user unquestionably given that this user does not exists already
            if ( form.equals(FORM_REGISTER) && (map.get( "password" )).equals( map.get( "confirmPassword" ) ) && p.save() ) {
                p.setSessionId( request.getSession().getId() );
                p.giveDefaultAvatar();
                p.update();
                p.getAvatar().setOriginalHeight(256.0);
                p.getAvatar().setOriginalWidth(256.0);
                p.getAvatar().update();
                response.sendRedirect( "/group" );
            }
            else if(form.equals(FORM_INDEX) && p.save()) {
                p.setSessionId( request.getSession().getId() );
                p.giveDefaultAvatar();
                p.update();
                p.getAvatar().setOriginalHeight(256.0);
                p.getAvatar().setOriginalWidth(256.0);
                p.getAvatar().update();
                response.sendRedirect( "/group" );
            }

            else {
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
