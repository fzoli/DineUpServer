package com.dineup.rest.exception;

public class InvalidParameterException extends RestException {
    
    public InvalidParameterException(String message, String localizedMessage) {
        super(message, localizedMessage);
    }
    
    public InvalidParameterException(String message, String localizedMessage, Exception cause) {
        super(message, localizedMessage, cause);
    }
    
}
