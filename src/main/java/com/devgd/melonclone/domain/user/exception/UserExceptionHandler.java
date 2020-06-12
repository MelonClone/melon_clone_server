package com.devgd.melonclone.domain.user.exception;

import com.devgd.melonclone.global.error.ErrorCode;
import com.devgd.melonclone.global.error.ErrorResponse;
import com.devgd.melonclone.global.error.GlobalNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserVerifyException.class)
	public ResponseEntity<ErrorResponse> handleUserException(final UserVerifyException e) {
		// TODO logging
		final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GlobalNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleArtistException(final GlobalNotFoundException e) {
		// TODO logging
		final ErrorResponse response = ErrorResponse.of(ErrorCode.ENTITY_NOT_FOUND);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}