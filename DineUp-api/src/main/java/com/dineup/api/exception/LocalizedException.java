package com.dineup.api.exception;

import com.dineup.util.Strings;

public class LocalizedException extends Exception {

    private final String localizedMessage;
    
    public LocalizedException(String message, String localizedMessage) {
        super(message);
        this.localizedMessage = localizedMessage;
    }
    
    public LocalizedException(String message, String localizedMessage, Throwable cause) {
        super(message, cause);
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        if (Strings.isEmptyText(localizedMessage)) {
            return getMessage();
        }
        return localizedMessage;
    }
    
}
