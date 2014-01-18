package com.peerpen.framework.exception;

import javax.servlet.RequestDispatcher;

public class HttpException extends Throwable {

    private int statusCode;
    public HttpException(int statusCode, String message)
    {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode( int statusCode ) {
        this.statusCode = statusCode;
    }
}
