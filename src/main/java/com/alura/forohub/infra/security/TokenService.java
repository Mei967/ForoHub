package com.alura.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.alura.forohub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(usuario.getLogin())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(algoritmo);
    }

    public String getSubject(String tokenJWT) {
        if (tokenJWT == null) {
            throw new RuntimeException("Token JWT inválido");
        }

        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .build()
                .verify(tokenJWT)
                .getSubject();
    }
}