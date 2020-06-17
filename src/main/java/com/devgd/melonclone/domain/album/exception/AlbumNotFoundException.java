package com.devgd.melonclone.domain.album.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class AlbumNotFoundException extends GlobalNotFoundException {
	public AlbumNotFoundException(String target) {
		super("Album Number "+target + " is not found");
	}
	
}