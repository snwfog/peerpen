package com.peerpen.framework.exception;

import javax.servlet.RequestDispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpException extends Throwable {
    static final Logger logger = LoggerFactory.getLogger( HttpException.class );

    private int statusCode;
    public HttpException(int statusCode, String message)
    {
        super(message);
        this.statusCode = statusCode;
        logger.error("An exception has occurred " + message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode( int statusCode ) {
        this.statusCode = statusCode;
    }
}
