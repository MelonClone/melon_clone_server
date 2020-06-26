package com.devgd.melonclone.global.error;

import javax.persistence.EntityNotFoundException;

public abstract class GlobalNotFoundException extends EntityNotFoundException {

	/**
	 *
	 */
	private static final long serialVersionUID = -8997522273359530591L;

	public GlobalNotFoundException(String string) {
		super(string);
	}
}