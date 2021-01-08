package com.auth.utils.jwt;

import com.auth.utils.model.UserAuthModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

public class JWTUtil {

    private KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    public Function<UserAuthModel, String> buildJWTFunctionGenearator(Long expirationMinutes) {

        HashMap<String, Object> claims = new HashMap<>();

        return userCredentials -> Jwts.builder()
                .addClaims(claims)
                .setSubject(userCredentials.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(expirationMinutes))))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public Function<String, Jws<Claims>>  buildJWTFunctionValiadtor() {
        return jwt -> Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(jwt);
    }
}
