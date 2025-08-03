package com.erdal.exeptions;

public class AuthBadRequestException extends RuntimeException {



	private static final long serialVersionUID = 1L;

	private Long id;

	public AuthBadRequestException(String message) {
		super(message);
	}

	public AuthBadRequestException(String message, Long id) {
		super(message);
		this.id = id;

	}
}