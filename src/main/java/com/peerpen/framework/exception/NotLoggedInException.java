package com.peerpen.framework.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotLoggedInException extends HttpException {

    static final Logger logger = LoggerFactory.getLogger( NotLoggedInException.class );

    public NotLoggedInException( String reason ) {
        this( 401, reason );
    }

    public NotLoggedInException() {
        this( 401, "User is not logged in, and must be logged in to continue." );
    }

    public NotLoggedInException( int statusCode, String message ) {
        super( statusCode, message );
        logger.error( message );
    }
}
