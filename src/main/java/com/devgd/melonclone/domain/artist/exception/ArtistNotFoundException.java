package com.devgd.melonclone.domain.artist.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class ArtistNotFoundException extends GlobalNotFoundException {
	public ArtistNotFoundException(String target) {
		super("Artist Number "+target + " is not found");
	}
	
}