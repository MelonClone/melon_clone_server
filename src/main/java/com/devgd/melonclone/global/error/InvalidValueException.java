package com.devgd.melonclone.global.error;

public abstract class InvalidValueException extends BusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7588305837333456417L;

	public InvalidValueException(String msg) {
		super(msg, ErrorCode.INVALID_INPUT_VALUE);
	}
	
	public InvalidValueException(String msg, ErrorCode errorCode) {
		super(msg, errorCode);
	}
}