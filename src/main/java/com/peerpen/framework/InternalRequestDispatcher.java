package com.peerpen.framework;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalRequestDispatcher implements RequestDispatcher {

    static final Logger logger = LoggerFactory.getLogger( InternalRequestDispatcher.class );
    private RequestDispatcher rd;

    public InternalRequestDispatcher( RequestDispatcher rd ) {
        this.rd = rd;
    }

    public void internalForward( ServletRequest request, ServletResponse response )
            throws ServletException, IOException {
        ((InternalHttpServletRequest) request).injectSecret();
        logger.warn( "Internally forwarding the request to "
                + ((InternalHttpServletRequest) request).getRequestURI() );
        this.rd.forward( request, response );
    }

    public void checkedForward( ServletRequest request, ServletResponse response )
            throws ServletException, IOException {
        logger.warn( "Processing a checked forward request to "
                + ((InternalHttpServletRequest) request).getRequestURI() );
        this.rd.forward( request, response );
    }

    @Override
    public void forward( ServletRequest request, ServletResponse response )
            throws ServletException, IOException {
        internalForward( request, response );
    }

    @Override
    public void include( ServletRequest request, ServletResponse response ) throws ServletException, IOException {
        this.rd.include( request, response );

    }
}
