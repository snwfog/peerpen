package com.peerpen.framework.exception;

public class ParameterCollisionException extends HttpException {

    public ParameterCollisionException( String key ) {
        super( 409, "Conflict in parameter for key " + key );
    }
}
