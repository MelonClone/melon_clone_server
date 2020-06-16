package com.devgd.melonclone.domain.artist.exception;

import com.devgd.melonclone.global.error.BusinessException;
import com.devgd.melonclone.global.error.ErrorCode;

public class ArtistPermissionException extends BusinessException {
	public ArtistPermissionException() {
		super("Artist permission deny", ErrorCode.HANDLE_ACCESS_DENIED);
	}
	
}