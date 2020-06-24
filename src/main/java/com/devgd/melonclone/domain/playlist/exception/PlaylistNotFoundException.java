package com.devgd.melonclone.domain.playlist.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class PlaylistNotFoundException extends GlobalNotFoundException {
	public PlaylistNotFoundException(String target) {
		super("User "+target + " is not found");
	}
	
}