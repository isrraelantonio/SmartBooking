package com.SmartBooking.security;


import com.SmartBooking.Modelos.Usuario.Usuario;
import com.SmartBooking.exception.ValidacaoException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import com.auth0.jwt.JWT;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API SmartBooking")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    public String getsubject(String tokenJwt){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API SmartBooking")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("Erro ao verificar o token ");
            throw new ValidacaoException("Token JWT inválido ou expirado!");
        }
    }

//    private Instant dataExpiracao() {
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//    }

    private Instant dataExpiracao() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }


}
