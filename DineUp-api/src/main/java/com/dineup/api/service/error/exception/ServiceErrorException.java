package com.dineup.api.service.error.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.SERVICE_ERROR)
public class ServiceErrorException extends Exception {

}
