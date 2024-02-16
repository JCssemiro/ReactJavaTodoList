package com.example.todolistspringsecurity.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.todolistspringsecurity.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${spring.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("TodoList")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        }catch(JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT: ",e);
        }
    }
    public String getSubject(String tokenJWT){
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("TodoList")
                .build()
                .verify(tokenJWT)
                .getSubject();


    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-04:00"));
    }
}