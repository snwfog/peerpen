package com.peerpen.framework.exception;

public class HttpSessionException extends HttpException {

    public HttpSessionException( String msg ) {
        super( 400, msg );
    }
}
