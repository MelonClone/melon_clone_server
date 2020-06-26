package com.devgd.melonclone.domain.artist.exception;

import com.devgd.melonclone.global.error.BusinessException;
import com.devgd.melonclone.global.error.ErrorCode;

public class ArtistPermissionException extends BusinessException {
	/**
	 *
	 */
	private static final long serialVersionUID = -2365085738584787241L;

	public ArtistPermissionException() {
		super("Artist permission deny", ErrorCode.HANDLE_ACCESS_DENIED);
	}
	
}