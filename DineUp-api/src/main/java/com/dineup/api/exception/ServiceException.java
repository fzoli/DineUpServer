package com.dineup.api.exception;

public class ServiceException extends DetailedException {
    
    private final int statusCode;
    private final String key;
    
    public ServiceException(int statusCode, String key, String message, String localizedMessage, String description, String localizedDescription) {
        super(message, localizedMessage, description, localizedDescription);
        this.statusCode = statusCode;
        this.key = key;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getKey() {
        return key;
    }
    
}
