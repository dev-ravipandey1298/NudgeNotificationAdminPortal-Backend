package com.hdfc.notificationadminportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CUGUserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CUGUserAlreadyExistsException(String message) {
		super(message);
	}
}
