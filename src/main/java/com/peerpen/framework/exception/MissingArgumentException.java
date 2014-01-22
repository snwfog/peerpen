package com.peerpen.framework.exception;

import org.apache.commons.lang3.StringUtils;

public class MissingArgumentException extends HttpException {

    public MissingArgumentException( String s ) {
        super(400, "Missing argument(s) for " + s);
    }

    public MissingArgumentException( String ... args) {
        super(400, StringUtils.join(args, ", "));
    }
}
