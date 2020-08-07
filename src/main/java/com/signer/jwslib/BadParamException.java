package com.signer.jwslib;

@SuppressWarnings("serial")
public class BadParamException extends Exception {
	public BadParamException() {
		super();
	}
	
	public BadParamException(String message) {
		super(message);
	}
	
	public BadParamException(Throwable a) {
		super(a);
	}
}
