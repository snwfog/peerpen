package com.peerpen.framework.exception;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotLoggedInException extends HttpException {

    static final Logger logger = LoggerFactory.getLogger( NotLoggedInException.class );

    public NotLoggedInException( HttpSession session ) {
        this( 401, "User is not logged in, and must be logged in to continue.", session );
    }

    public NotLoggedInException( int statusCode, String message, HttpSession session ) {
        super( statusCode, message );
        logger.error( message );
    }
}
