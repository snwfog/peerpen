package com.peerpen.controller;

import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.NotLoggedInException;
import com.peerpen.model.Peer;

import java.io.IOException;

import javax.el.MethodNotFoundException;
import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( LoginController.class );

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        throw new MethodNotFoundException( "Cannot call method GET on the login controller" );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            if ( Peer.isValidLogin( request ) ) {
                response.sendRedirect( "/peer/2/profile.do" );
                //request.getRequestDispatcher( "/peer/2/profile" ).forward( request, response );
            }
        } catch ( OperationNotSupportedException | NotLoggedInException | MissingArgumentException e ) {
            logger.error( "", e );
            ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response, e );
        }
    }
}
