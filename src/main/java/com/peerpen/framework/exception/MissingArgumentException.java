package com.peerpen.framework.exception;

public class MissingArgumentException extends HttpException {

    public MissingArgumentException( String s ) {
        super(400, s);
    }
}
