package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.UNSPECIFIED_IDENTIFIER)
public class UnspecifiedIdentifierException extends InvalidParameterException {
    public UnspecifiedIdentifierException(String message) {
        super(message);
    }
}
