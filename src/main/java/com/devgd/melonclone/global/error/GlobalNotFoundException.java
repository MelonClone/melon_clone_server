package com.devgd.melonclone.global.error;

import javax.persistence.EntityNotFoundException;

public abstract class GlobalNotFoundException extends EntityNotFoundException {

	public GlobalNotFoundException(String string) {
		super(string);
	}
}