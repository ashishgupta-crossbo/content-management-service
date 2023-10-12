package com.cms.service.exception.handler;

import com.cms.service.common.ErrorCode;
public class AbstractException extends RuntimeException {

    private final ErrorCode errorCode;

    public AbstractException(ErrorCode errorCode, Exception cause) {
        super(errorCode.name(), cause);
        this.errorCode = errorCode;
    }

    public AbstractException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }

}