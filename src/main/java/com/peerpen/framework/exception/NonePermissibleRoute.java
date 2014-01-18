package com.peerpen.framework.exception;

public class NonePermissibleRoute extends HttpException
{
    public NonePermissibleRoute(String message)
    {
        super(404, message);
    }
}
