package com.spring.demo.exception;

public class IllegalUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalUserException(String msg) {
		super(msg);
	}

}
