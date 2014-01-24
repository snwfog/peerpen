package com.peerpen.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionDeniedException extends HttpException {

    static final Logger logger = LoggerFactory.getLogger( PermissionDeniedException.class );

    public PermissionDeniedException(String msg) {
        this( 550, msg );
    }

    public PermissionDeniedException(int statusCode, String message) {
        super( statusCode, message );
        logger.error( message );
    }
}
