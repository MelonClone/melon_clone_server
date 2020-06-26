package com.devgd.melonclone.global.error;

public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6764540562616060958L;
    private ErrorCode errorCode;

    public BusinessException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
