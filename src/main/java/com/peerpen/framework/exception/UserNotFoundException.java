package com.peerpen.framework.exception;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends HttpException {

    static final Logger logger = LoggerFactory.getLogger( UserNotFoundException.class );
    public UserNotFoundException( HttpSession session)
    {
        this(404, "Cannot find user with associate session ID of " + session.getId(), session);
    }

    public UserNotFoundException( int statusCode, String message, HttpSession session ) {
        super( statusCode, message );
        logger.error( message );
    }
}
