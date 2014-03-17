package com.peerpen.framework.exception;

/**
 * Created by snw on 1/21/2014.
 */
public class ServletPathDontExistException extends HttpException {

    public ServletPathDontExistException( String path ) {
        super( 404, path );
    }
}
