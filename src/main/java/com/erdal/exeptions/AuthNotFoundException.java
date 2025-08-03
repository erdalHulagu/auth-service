package com.erdal.exeptions;

public class AuthNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long id;

	public AuthNotFoundException(String message) {
		super(message);
	}

	public AuthNotFoundException(String message, Long id) {
		super(message);
		this.id = id;

	}
}