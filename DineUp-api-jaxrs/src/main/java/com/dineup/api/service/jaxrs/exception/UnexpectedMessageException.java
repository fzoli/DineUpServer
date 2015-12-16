package com.dineup.api.service.jaxrs.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;
import java.io.IOException;

@ErrorDescription(key = ErrorKey.UNEXPECTED_MESSAGE)
public class UnexpectedMessageException extends IOException {

    public UnexpectedMessageException(Throwable cause) {
        super(cause);
    }
    
}
