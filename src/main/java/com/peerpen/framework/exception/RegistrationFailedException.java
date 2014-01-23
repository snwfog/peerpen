package com.peerpen.framework.exception;

import java.util.Map;

public class RegistrationFailedException extends HttpException {

    public RegistrationFailedException( Map<String, Object> map ) {
        super( 406, "Failed register using with " + map );
    }

    public RegistrationFailedException( String msg ) {
        super( 406, msg );
    }

}
