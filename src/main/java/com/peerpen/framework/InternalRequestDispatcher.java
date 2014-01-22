package com.peerpen.framework;

import com.google.common.base.Throwables;
import com.peerpen.framework.exception.HttpException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        logger.warn( "Internally forwarding the request to " + ((InternalHttpServletRequest) request).getRequestURI() );
        this.rd.forward( request, response );
    }

    public void checkedForward( ServletRequest request, ServletResponse response )
            throws ServletException, IOException {
        logger.warn(
                "Processing a checked forward request to " + ((InternalHttpServletRequest) request).getRequestURI() );
        this.rd.forward( request, response );
    }

    public void forwardError( ServletRequest request, ServletResponse response, Throwable e ) {
        logger.error( "Application thrown an error " + e.toString() );
        this.redirectError( (HttpServletRequest) request, (HttpServletResponse) response, e );

    }

    private void redirectError( HttpServletRequest request, HttpServletResponse response, Throwable e ) {
        request.setAttribute( "exception", e );
        if ( e instanceof HttpException ) {
            HttpException exc = (HttpException) e;
            this.redirect( request, response, exc.getStatusCode(), "/error", e.toString() );
        } else {
            this.redirectError( request, response );
        }
    }

    private void redirect( HttpServletRequest request, HttpServletResponse response, int code, String path,
            String reason ) {
        try {

            RequestDispatcher dispatcher = request.getRequestDispatcher( path );
            response.setStatus( code );
            request.setAttribute( "reason", reason );
            request.setAttribute( "errorCode", code );
            dispatcher.forward( request, response );
            logger.warn( "Denied user trying to access an invalid path " + request.getRequestURI() );

        } catch ( IOException e ) {
            logger.error( "Could not locate path " + path );

        } catch ( ServletException e ) {
            logger.error( "Could not forward user to " + path );
        }
    }

    public void redirectError( HttpServletRequest request, HttpServletResponse response ) {
        Throwable e = (Throwable) request.getAttribute( "exception" );
        if ( e != null && e instanceof HttpException ) {
            redirect( request, response, ((HttpException) e).getStatusCode(), "/error", e.toString() );
        } else {
            redirect( request, response, 404, "/error", "Something went wrong when accessing this page." );
        }

    }

    @Override
    public void forward( ServletRequest request, ServletResponse response ) throws ServletException, IOException {
        internalForward( request, response );
    }

    @Override
    public void include( ServletRequest request, ServletResponse response ) throws ServletException, IOException {
        this.rd.include( request, response );

    }
}
