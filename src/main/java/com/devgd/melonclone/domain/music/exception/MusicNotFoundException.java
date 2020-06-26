package com.devgd.melonclone.domain.music.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class MusicNotFoundException extends GlobalNotFoundException {
	/**
	 *
	 */
	private static final long serialVersionUID = 2537872260647101979L;

	public MusicNotFoundException(String target) {
		super("Music Number "+target + " is not found");
	}
	
}