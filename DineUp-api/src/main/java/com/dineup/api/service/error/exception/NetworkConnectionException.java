package com.dineup.api.service.error.exception;

import com.dineup.api.service.error.ErrorDescription;
import com.dineup.api.service.error.ErrorKey;
import java.io.IOException;

@ErrorDescription(key = ErrorKey.NETWORK_CONNECTION_ERROR)
public class NetworkConnectionException extends IOException {

    public NetworkConnectionException(Throwable cause) {
        super(cause);
    }
    
}
