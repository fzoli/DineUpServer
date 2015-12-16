package com.dineup.api.service.error.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.SERVICE_NOT_FOUND)
public class ServiceNotFoundException extends Exception {
    
}
