package com.peerpen.framework;

import com.google.common.base.Throwables;
import com.peerpen.framework.exception.AttributeCollisionException;
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
        String appSecret = (String) this.getAttribute( "appSecret" );
        String sessionAppSecret = this.getSession().getServletContext().getInitParameter( "app-secret" );

        if ( !(appSecret != null && appSecret.equals( sessionAppSecret )) ) {
            this.setAttribute( "appSecret", sessionAppSecret );
            logger.warn( "Injecting secret into the http servlet request" );
        }

        return this;
    }

    @Override
    public RequestDispatcher getRequestDispatcher( String path ) {
        return new InternalRequestDispatcher( super.getRequestDispatcher( path ) );
    }

    private boolean expectPresenceOf( String arg ) throws MissingArgumentException {
        boolean hasArg = this.getParameter( arg ) != null && !this.getParameter( arg ).equalsIgnoreCase( "" ) &&
                !this.getParameter( arg ).equalsIgnoreCase( "null" );
        if ( !hasArg ) {
            throw new MissingArgumentException( arg );
        }

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

    @Override
    public void setAttribute( String name, Object o ) {
        try {
            // FIXME: Lame way of safe typing native catalina values
            if ( name.indexOf( "org.apache.catalina" ) == -1 && name.indexOf( "internal." ) == -1 &&
                    this.getAttribute( name ) != null ) {
                throw new AttributeCollisionException( name, this.getAttribute( name ) );
            } else {
                super.setAttribute( name, o );
            }
        } catch ( AttributeCollisionException e ) {
            logger.error( "Attribute collision, new object " + o.toString() + " is ignored", e );
            Throwables.propagate( e );
        }
    }
}
