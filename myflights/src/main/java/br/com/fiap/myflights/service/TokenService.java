package br.com.fiap.myflights.service;

import br.com.fiap.myflights.models.Credencial;
import br.com.fiap.myflights.models.Token;
import br.com.fiap.myflights.models.User;
import br.com.fiap.myflights.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    UserRepository userRepository;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var jwt = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("MyFlights")
                .withExpiresAt(Instant.now().plus(4, ChronoUnit.HOURS))
                .sign(alg);
        return new Token(jwt, "JWT", "Bearer");
    }

    public User validate(String token) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var email = JWT.require(alg)
                .withIssuer("MyFlights")
                .build()
                .verify(token)
                .getSubject();

        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new JWTVerificationException("user não encontrado"));
    }

    public String getToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.replace("Bearer ", "");
    }

}
