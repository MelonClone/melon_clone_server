package com.devgd.melonclone.domain.playlist.exception;

import com.devgd.melonclone.global.error.GlobalNotFoundException;

public class PlaylistNotFoundException extends GlobalNotFoundException {
	/**
	 *
	 */
	private static final long serialVersionUID = 6984714068556156496L;

	public PlaylistNotFoundException(String target) {
		super("User "+target + " is not found");
	}
	
}