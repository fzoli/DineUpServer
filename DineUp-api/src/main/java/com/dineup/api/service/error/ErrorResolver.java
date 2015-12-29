package com.dineup.api.service.error;

import com.dineup.api.exception.ClientException;
import com.dineup.api.exception.DetailedException;

public class ErrorResolver {

    private final String languageCode;
    
    public ErrorResolver(String languageCode) {
        this.languageCode = languageCode;
    }
    
    public DetailedException resolveError(Exception ex) {
        ErrorDetailResolver detailResolver = ErrorDetailResolver.getInstance();
        ErrorDetail detail = detailResolver.resolveError(ex, languageCode);
        return new ClientException(
                detail.getKey().name(), 
                detail.getMessage().getMessage(), 
                detail.getLocalizedMessage().getMessage(), 
                detail.getMessage().getDescription(), 
                detail.getLocalizedMessage().getDescription(), 
                ex
        );
    }
    
}
