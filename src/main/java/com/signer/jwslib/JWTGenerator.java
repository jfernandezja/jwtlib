package com.signer.jwslib;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTGenerator {
	private Algorithm algorithm;
	private String issuer;
	
	public JWTGenerator(RSAPublicKey publicKey, RSAPrivateKey privateKey, String issuer) throws Exception {
		algorithm = Algorithm.RSA256(publicKey, privateKey);
		this.issuer = issuer;
	}
	
	public String build(String subject) throws Exception {
	    return JWT.create()
	        .withIssuer(issuer)
	        .withSubject(subject)
	        .sign(algorithm);
	}
}
