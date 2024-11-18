package com.api.clinica.infra.security;

import com.api.clinica.domain.data.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("clinica")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            return exception.getMessage();
        }
    }

    public String verifyToken(String token) {
        DecodedJWT verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            verifier = JWT.require(algorithm)
                    .withIssuer("clinica")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception){
           return exception.getMessage();
        }
        return verifier.getSubject();
    }
}
