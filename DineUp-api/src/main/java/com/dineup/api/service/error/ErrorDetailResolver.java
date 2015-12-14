package com.dineup.api.service.error;

import com.dineup.util.Exceptions;
import java.util.Locale;

public class ErrorDetailResolver {
    
    private static final String DEFAULT_LANGUAGE = "en";
    
    private static final ErrorDetailResolver INSTANCE = new ErrorDetailResolver();

    public static ErrorDetailResolver getInstance() {
        return INSTANCE;
    }
    
    public ErrorDetail resolveError(Exception ex, String languageCode) {
        ErrorLocalizer errorLocalizer = ErrorLocalizer.getInstance();
        Throwable rootCause = Exceptions.getRootCause(ex);
        ErrorKey key = ErrorKey.GENERAL_ERROR;
        String note = rootCause.getMessage();
        
        ErrorDescription resolvedDescription = getDescription(ex);
        if (resolvedDescription != null) {
            key = resolvedDescription.key();
        }
        
        ErrorMessage message = errorLocalizer.getMessage(key, new Locale(DEFAULT_LANGUAGE));
        ErrorMessage localizedMessage = null;
        if (languageCode != null && !languageCode.equalsIgnoreCase(DEFAULT_LANGUAGE)) {
            localizedMessage = errorLocalizer.getMessage(key, new Locale(languageCode));
        }
        
        return ErrorDetail.newBuilder()
                .key(key)
                .note(note)
                .message(message)
                .localizedMessage(localizedMessage)
                .build();
    }
    
    private ErrorDescription getDescription(Exception ex) {
        ErrorDescription description = ex.getClass().getAnnotation(ErrorDescription.class);
        return description;
    }
    
}