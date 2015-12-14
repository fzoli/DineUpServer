package com.dineup.api.exception;

public class ClientException extends DetailedException {
    
    public ClientException(String key, String message, String localizedMessage, String description, String localizedDescription, Throwable cause) {
        super(key, message, localizedMessage, description, localizedDescription, cause);
    }
    
}
