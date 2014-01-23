package com.peerpen.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends HttpException {

    static final Logger logger = LoggerFactory.getLogger( UserNotFoundException.class );

    public UserNotFoundException( String msg ) {
        this( 404, msg );
    }

    public UserNotFoundException( int statusCode, String message ) {
        super( statusCode, message );
        logger.error( message );
    }
}
