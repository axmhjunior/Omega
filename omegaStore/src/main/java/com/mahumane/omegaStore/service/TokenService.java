package com.mahumane.omegaStore.service;

import java.time.Instant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mahumane.omegaStore.model.UsersModel;

@Service
public class TokenService {

	
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateTokenJWT(UsersModel user) {
		return JWT.create()
				.withIssuer("users")
				.withSubject(user.getName())
				.withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plusSeconds(500L))
				.sign(Algorithm.HMAC256(secret));
	}

	public String getSubject(String token) {
		// TODO Auto-generated method stub
		return JWT.require(Algorithm.HMAC256(secret))
				.withIssuer("users")
				.build()
				.verify(token).getSubject();
	}
}
