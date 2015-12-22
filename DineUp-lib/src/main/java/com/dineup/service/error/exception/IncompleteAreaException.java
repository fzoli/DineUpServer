package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.INCOMPLETE_AREA)
public class IncompleteAreaException extends InvalidParameterException {
    
}
