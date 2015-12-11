package com.dineup.ejb.error;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;
import com.dineup.service.error.ErrorStatus;
import com.dineup.util.Exceptions;
import java.util.Locale;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

@Singleton
@LocalBean
public class ErrorDetailResolver {
    
    private static final String DEFAULT_LANGUAGE = "en";
    
    @EJB
    private ErrorLocalizer errorLocalizer;
    
    public ErrorDetail resolveError(Exception ex, String languageCode) {
        Throwable rootCause = Exceptions.getRootCause(ex);
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        ErrorKey key = ErrorKey.GENERAL_ERROR;
        String note = rootCause.getMessage();
        
        ErrorDescription resolvedDescription = getDescription(ex);
        if (resolvedDescription != null) {
            key = resolvedDescription.key();
        }
        
        ErrorStatus resolvedStatus = getStatus(ex);
        if (resolvedStatus != null) {
            status = resolvedStatus.status();
        }
        
        ErrorMessage message = errorLocalizer.getMessage(key, new Locale(DEFAULT_LANGUAGE));
        ErrorMessage localizedMessage = null;
        if (languageCode != null && !languageCode.equalsIgnoreCase(DEFAULT_LANGUAGE)) {
            localizedMessage = errorLocalizer.getMessage(key, new Locale(languageCode));
        }
        
        return ErrorDetail.newBuilder()
                .status(status)
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
    
    private ErrorStatus getStatus(Exception ex) {
        ErrorStatus status = ex.getClass().getAnnotation(ErrorStatus.class);
        return status;
    }
    
}
