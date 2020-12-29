package com.auth.app.util;

import com.auth.app.domain.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTUtil {

    private KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    @Value("${jwt.secret}")
    private String privateKey;

    @Value("${jwt.crsfToken}")
    private String crsfToken;

    @Value("${jwt.expiration}")
    private Long expirationMinutes;

    public String generateJwt(UserCredentials userCredentials) {

        HashMap<String, Object> claims = new HashMap<>();

        Date creationDate = new Date();
        Date expirationDate = Date.from(Instant.now().plus(Duration.ofMinutes(expirationMinutes)));

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userCredentials.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public Jws<Claims> validateJwt(String jwt) {

        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(jwt);
    }
}
