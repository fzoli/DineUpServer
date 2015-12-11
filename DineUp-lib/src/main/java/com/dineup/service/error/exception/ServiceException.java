package com.dineup.service.error.exception;

class ServiceException extends RuntimeException {

    ServiceException() {
        super();
    }

    ServiceException(String message) {
        super(message);
    }
    
    ServiceException(Exception cause) {
        super(cause);
    }
    
    ServiceException(String message, Exception cause) {
        super(message, cause);
    }
    
}
