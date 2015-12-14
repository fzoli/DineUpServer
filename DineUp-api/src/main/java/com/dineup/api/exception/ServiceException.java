package com.dineup.api.exception;

public class ServiceException extends DetailedException {
    
    private final int statusCode;
    
    public ServiceException(int statusCode, String key, String message, String localizedMessage, String description, String localizedDescription) {
        super(key, message, localizedMessage, description, localizedDescription);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
}
