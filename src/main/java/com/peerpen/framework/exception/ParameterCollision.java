package com.peerpen.framework.exception;

public class ParameterCollision extends HttpException {

    public ParameterCollision( String key ) {
        super( 409, "Conflict in parameter for key " + key );
    }
}
