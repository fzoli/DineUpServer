package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorStatus;
import javax.ws.rs.core.Response;

@ErrorStatus(status = Response.Status.BAD_REQUEST)
class InvalidParameterException extends ServiceException {

    InvalidParameterException() {
        super();
    }
    
    InvalidParameterException(String message) {
        super(message);
    }
    
    InvalidParameterException(String message, Exception cause) {
        super(message, cause);
    }
    
}
