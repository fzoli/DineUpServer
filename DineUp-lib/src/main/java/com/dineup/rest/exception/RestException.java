package com.dineup.rest.exception;

public class RestException extends RuntimeException {

    private final String localizedMessage;
    
    public RestException(String message, String localizedMessage) {
        super(message);
        this.localizedMessage = localizedMessage;
    }
    
    public RestException(Exception cause) {
        super(cause);
        localizedMessage = null;
    }
    
    public RestException(String message, String localizedMessage, Exception cause) {
        super(message, cause);
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        if (localizedMessage == null) {
            return getMessage();
        }
        return localizedMessage;
    }
    
}
