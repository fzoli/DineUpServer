package com.dineup.api.exception;

import com.dineup.util.Strings;

public class DetailedException extends LocalizedException {
    
    private final String description;
    private final String localizedDescription;
    
    public DetailedException(String message, String localizedMessage, String description, String localizedDescription) {
        super(message, localizedMessage);
        this.description = description;
        this.localizedDescription = localizedDescription;
    }
    
    public DetailedException(String message, String localizedMessage, String description, String localizedDescription, Throwable cause) {
        super(message, localizedMessage, cause);
        this.description = description;
        this.localizedDescription = localizedDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalizedDescription() {
        if (Strings.isEmptyText(localizedDescription)) {
            return description;
        }
        return localizedDescription;
    }
    
}
