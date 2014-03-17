package com.peerpen.framework.exception;

public class NonPermissibleRoute extends HttpException {

    public NonPermissibleRoute( String message ) {
        super( 404, message );

    }
}
