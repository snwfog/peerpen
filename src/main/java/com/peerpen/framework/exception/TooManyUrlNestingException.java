package com.peerpen.framework.exception;

public class TooManyUrlNestingException extends HttpException {

    public TooManyUrlNestingException( String s ) {
        super(400, s);
    }
}
