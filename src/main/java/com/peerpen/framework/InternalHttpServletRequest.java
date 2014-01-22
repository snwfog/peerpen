package com.peerpen.framework;

import com.peerpen.framework.exception.MissingArgumentException;

import javax.naming.OperationNotSupportedException;
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
        logger.warn( "Injecting secret into the http servlet request" );
        return this;
    }

    @Override
    public RequestDispatcher getRequestDispatcher( String path ) {
        return new InternalRequestDispatcher( super.getRequestDispatcher( path ) );
    }

    private boolean expectPresenceOf( String arg ) throws MissingArgumentException {
        boolean hasArg = this.getParameter( arg ) != null && !this.getParameter( arg ).equalsIgnoreCase( "" ) &&
                !this.getParameter( arg ).equalsIgnoreCase( "null" );
        if (!hasArg) throw new MissingArgumentException( arg );

        return true;
    }

    public boolean expectPresenceOf( String... args ) throws MissingArgumentException {
        boolean isPresent = true;
        for ( String arg : args ) {
            isPresent = isPresent && expectPresenceOf( arg );
        }

        return isPresent;
    }

    public static InternalHttpServletRequest transform( HttpServletRequest request )
            throws OperationNotSupportedException {
        if ( request instanceof InternalHttpServletRequest ) {
            return (InternalHttpServletRequest) request;
        } else {
            throw new OperationNotSupportedException(
                    "Trying to elevate the status of a HttpRequest on an invalid object of " +
                            request.getClass().toString() );
        }
    }

}
