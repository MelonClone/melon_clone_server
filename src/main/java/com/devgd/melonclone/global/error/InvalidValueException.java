package com.devgd.melonclone.global.error;

public abstract class InvalidValueException extends BusinessException {

	public InvalidValueException(String msg) {
		super(msg, ErrorCode.INVALID_INPUT_VALUE);
	}
	
	public InvalidValueException(String msg, ErrorCode errorCode) {
		super(msg, errorCode);
	}
}