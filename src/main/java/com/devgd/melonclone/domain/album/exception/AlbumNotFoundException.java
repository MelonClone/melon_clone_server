package com.devgd.melonclone.domain.album.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class AlbumNotFoundException extends GlobalNotFoundException {
	/**
	 *
	 */
	private static final long serialVersionUID = -3966672244519824847L;

	public AlbumNotFoundException(String target) {
		super("Album Number "+target + " is not found");
	}
	
}