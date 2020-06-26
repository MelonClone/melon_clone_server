package com.devgd.melonclone.domain.user.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class UserNotFoundException extends GlobalNotFoundException {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 7944250683394366359L;

	public UserNotFoundException(String target) {
		super("User "+target + " is not found");
	}
	
}