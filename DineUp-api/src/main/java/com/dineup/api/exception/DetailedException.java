package com.dineup.api.exception;

import com.dineup.util.Strings;

public class DetailedException extends LocalizedException {
    
    private final String key;
    private final String description;
    private final String localizedDescription;
    
    public DetailedException(String key, String message, String localizedMessage, String description, String localizedDescription) {
        super(message, localizedMessage);
        this.key = key;
        this.description = description;
        this.localizedDescription = localizedDescription;
    }
    
    public DetailedException(String key, String message, String localizedMessage, String description, String localizedDescription, Throwable cause) {
        super(message, localizedMessage, cause);
        this.key = key;
        this.description = description;
        this.localizedDescription = localizedDescription;
    }

    public String getKey() {
        return key;
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

    @Override
    public String toString() {
        return String.format("%s(key=%s, message=\"%s\", description=\"%s\")", getClass().getSimpleName(), getKey(), getLocalizedMessage(), getLocalizedDescription());
    }
    
}
