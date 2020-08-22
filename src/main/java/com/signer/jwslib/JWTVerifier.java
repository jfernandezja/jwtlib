package com.signer.jwslib;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JWTVerifier {
	public Algorithm algorithm;
	
	public class Claims {
		private String issuer;
		private String subject;
		private Map<String,Claim> claims;
		
		private Claims(String issuer, String subject, Map<String,Claim> claims) {
			super();
			this.issuer = issuer;
			this.subject = subject;
			this.claims = claims;
		}

		public String getIssuer() {
			return issuer;
		}

		public String getSubject() {
			return subject;
		}
		
		public String get(String name) {
			if(!claims.containsKey(name))
				return null;
			return claims.get(name).asString();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public JWTVerifier(RSAPublicKey publicKey) throws Exception {
		algorithm = Algorithm.RSA256(publicKey);
	}
	
	public Claims verify(String jwt) throws Exception {
		if(jwt == null)
			throw new BadParamException("Null jwt token");
		com.auth0.jwt.interfaces.JWTVerifier verifier = JWT.require(algorithm).build();
		try {
			DecodedJWT token = verifier.verify(jwt);
			return new Claims(token.getIssuer(), token.getSubject(), token.getClaims());
		} catch(JWTVerificationException e) {
			throw new InvalidJwtSignatureException(e);
		}
	}
}
