package com.devgd.melonclone.domain.user.exception;

import com.devgd.melonclone.global.error.InvalidValueException;

public class UserVerifyException extends InvalidValueException {
	public UserVerifyException() {
		super("Bad Request");
	}
	
}