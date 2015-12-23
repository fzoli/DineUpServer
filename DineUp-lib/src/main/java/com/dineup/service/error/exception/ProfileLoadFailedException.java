package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;
import com.dineup.service.error.ErrorStatus;
import javax.ws.rs.core.Response;

@ErrorDescription(key = ErrorKey.PROFILE_LOAD_FAILED)
@ErrorStatus(status = Response.Status.SERVICE_UNAVAILABLE)
public class ProfileLoadFailedException extends ServiceException {
    
}
