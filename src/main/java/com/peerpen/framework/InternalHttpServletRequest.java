package com.peerpen.framework;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalHttpServletRequest extends HttpServletRequestWrapper {

    private RequestDispatcher rd;
    static final Logger logger = LoggerFactory.getLogger( InternalHttpServletRequest.class );

    /**
     * Constructs a request object wrapping the given request.
     * @throws IllegalArgumentException if the request is null
     * @param request
     */
    public InternalHttpServletRequest( HttpServletRequest request ) {
        super( request );
    }

    public HttpServletRequest injectSecret() {
        this.setAttribute( "appSecret", this.getSession().getServletContext().getInitParameter( "app-secret" ) );
        logger.warn("Injecting secret into the http servlet request");
        return this;
    }

    @Override
    public RequestDispatcher getRequestDispatcher( String path ) {
        return new InternalRequestDispatcher( super.getRequestDispatcher( path ) );
    }
}
