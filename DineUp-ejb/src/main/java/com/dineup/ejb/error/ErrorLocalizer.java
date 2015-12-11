package com.dineup.ejb.error;

import com.dineup.service.error.ErrorKey;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class ErrorLocalizer {
    
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
