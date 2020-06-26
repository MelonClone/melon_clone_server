package com.devgd.melonclone.domain.user.exception;

import com.devgd.melonclone.global.error.InvalidValueException;

public class UserVerifyException extends InvalidValueException {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1152312458814079537L;

	public UserVerifyException() {
		super("Bad Request");
	}
	
}