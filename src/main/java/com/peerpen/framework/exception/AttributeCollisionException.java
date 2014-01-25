package com.peerpen.framework.exception;

public class AttributeCollisionException extends HttpException {

    public AttributeCollisionException( String key, Object object)
    {
        super(409, "Conflict in parameter for key " + key + ". The original value is " + object.toString());
    }
}
