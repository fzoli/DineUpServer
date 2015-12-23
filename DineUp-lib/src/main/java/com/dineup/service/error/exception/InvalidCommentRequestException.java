package com.dineup.service.error.exception;

import com.dineup.service.error.ErrorDescription;
import com.dineup.service.error.ErrorKey;

@ErrorDescription(key = ErrorKey.INVALID_COMMENT_REQUEST)
public class InvalidCommentRequestException extends InvalidParameterException {
    
}
