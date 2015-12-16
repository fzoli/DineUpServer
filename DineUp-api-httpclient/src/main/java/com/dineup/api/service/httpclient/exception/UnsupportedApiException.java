package com.dineup.api.service.httpclient.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.UNSUPPORTED_API)
public class UnsupportedApiException extends Exception {

    public UnsupportedApiException() {
    }
    
}
