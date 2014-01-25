package com.peerpen.controller;

import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.HttpSessionException;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.UserNotFoundException;
import com.peerpen.model.Peer;

import java.io.IOException;

import javax.el.MethodNotFoundException;
import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( LoginController.class );

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.getRequestDispatcher( "/view/login.jsp" ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            Peer p = null;
            if ( (p = Peer.isValidLogin( request )) != null ) {
                response.sendRedirect( "/peer/" + p.getId() + "/feed" );
            }
        } catch ( OperationNotSupportedException | MissingArgumentException | HttpSessionException e ) {
            logger.error( "", e );
            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response, e );
        } catch ( UserNotFoundException e ) {
            logger.error( "", e );
            for ( Cookie cook : request.getCookies() ) {
                if ( cook.getName().equalsIgnoreCase( "failedAttempt" ) ) {
                    int attemptCount = Integer.valueOf( cook.getValue() );
                    response.addCookie( new Cookie( "failedAttempt", String.valueOf( ++attemptCount ) ) );
                } else {
                    response.addCookie( new Cookie( "failedAttempt", "1" ) );
                }
            }

            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response, e );
        }
    }
}
