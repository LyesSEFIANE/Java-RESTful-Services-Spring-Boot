package com.restfulservice.application.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
