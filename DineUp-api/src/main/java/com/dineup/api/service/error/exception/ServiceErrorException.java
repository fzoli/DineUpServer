package com.dineup.api.service.error.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

@ErrorDescription(key = ErrorKey.SERVICE_ERROR)
public class ServiceErrorException extends InternalServerErrorException {
    
    public ServiceErrorException(Response response) {
        super(response);
    }
    
}
