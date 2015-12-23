package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;
import com.dineup.service.error.ErrorStatus;
import javax.ws.rs.core.Response;

@ErrorDescription(key = ErrorKey.OBJECT_NOT_FOUND)
@ErrorStatus(status = Response.Status.SERVICE_UNAVAILABLE)
public class ObjectNotFoundException extends ServiceException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
    
}
