package com.dineup.api.service.error;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorLocalizer {
    
    private static final ErrorLocalizer INSTANCE = new ErrorLocalizer();

    public static ErrorLocalizer getInstance() {
        return INSTANCE;
    }

    private ErrorLocalizer() {
    }
    
    public ErrorMessage getMessage(ErrorKey errorKey, Locale locale) {
        try {
            ResourceBundle messages = ResourceBundle.getBundle("error.error_messages", locale);
            ResourceBundle descriptions = ResourceBundle.getBundle("error.error_descriptions", locale);
            String key = errorKey.name();
            String message;
            String description = null;
            if (messages.containsKey(key)) {
                message = messages.getString(key);
            }
            else {
                return null;
            }
            if (descriptions.containsKey(key)) {
                description = descriptions.getString(key);
            }
            return new ErrorMessage(message, description);
        }
        catch (MissingResourceException ex) {
            return null;
        }
    }
    
}
