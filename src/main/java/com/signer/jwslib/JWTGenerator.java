package com.signer.jwslib;

import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTGenerator {
	private Algorithm algorithm;
	private String issuer;
	
	@SuppressWarnings("deprecation")
	public JWTGenerator(RSAPrivateKey privateKey, String issuer) throws BadParamException {
		if(issuer == null)
			throw new BadParamException("Invalid issuer name");
		algorithm = Algorithm.RSA256(privateKey);
		this.issuer = issuer;
	}
	
	public String build(String subject) throws BadParamException {
		if(subject == null)
			throw new BadParamException("Invalid subject name");
	    return JWT.create()
	        .withIssuer(issuer)
	        .withSubject(subject)
	        .withIssuedAt(new Date())
	//        .withClaim("custom_claim", "wrong")
	        .sign(algorithm);
	}
}
