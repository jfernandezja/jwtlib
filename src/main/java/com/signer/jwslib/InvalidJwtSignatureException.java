package com.signer.jwslib;

@SuppressWarnings("serial")
public class InvalidJwtSignatureException extends Exception {
	public InvalidJwtSignatureException() {
		super();
	}
	
	public InvalidJwtSignatureException(Exception ex) {
		super(ex);
	}
}
