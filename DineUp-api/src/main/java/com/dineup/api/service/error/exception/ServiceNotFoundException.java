package com.dineup.api.service.error.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@ErrorDescription(key = ErrorKey.SERVICE_NOT_FOUND)
public class ServiceNotFoundException extends NotFoundException {
    public ServiceNotFoundException(Response response) {
        super(response);
    }
}
