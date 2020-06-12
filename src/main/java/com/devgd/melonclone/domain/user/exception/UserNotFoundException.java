package com.devgd.melonclone.domain.user.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class UserNotFoundException extends GlobalNotFoundException {
	public UserNotFoundException(String target) {
		super("User "+target + " is not found");
	}
	
}