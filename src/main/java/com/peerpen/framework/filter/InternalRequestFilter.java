package com.peerpen.framework.filter;

import com.peerpen.framework.InternalHttpServletRequest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalRequestFilter implements Filter {

    private FilterConfig fc;
    static final Logger logger = LoggerFactory.getLogger( InternalRequestFilter.class );

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        this.fc = filterConfig;
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        logger.info( "Determining the request source" );
        if ( request instanceof InternalHttpServletRequest ) {
            logger.info( "Internal request... Skipping the request mutation" );
            chain.doFilter( request, response );
        } else {
            logger.info( "Mutating into internal HTTP servlet request" );
            InternalHttpServletRequest internalRequest = new InternalHttpServletRequest( (HttpServletRequest) request );
            chain.doFilter( internalRequest, response );
        }

    }

    @Override
    public void destroy() {

    }
}
