package com.devgd.melonclone.domain.artist.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class ArtistNotFoundException extends GlobalNotFoundException {
	/**
	 *
	 */
	private static final long serialVersionUID = -4197082736934693015L;

	public ArtistNotFoundException(String target) {
		super("Artist Number "+target + " is not found");
	}
	
}