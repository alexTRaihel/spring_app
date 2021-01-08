package com.auth.utils.jwt;

import com.auth.utils.exeption.JWTokenException;
import com.auth.utils.model.UserAuthModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

public class JWTUtilFRomFile {

    public Function<UserAuthModel, String> buildTokenGeneratorFunction(Path keyFilePath, Long expirationMinutes){

        final PrivateKey privateKey = JWTUtilFRomFile.getPrivateKey(keyFilePath);

        return userCredentials -> Jwts.builder()
                .addClaims(getClaimsMap())
                .setSubject(userCredentials.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(expirationMinutes))))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public Function<String, Jws<Claims>> buildTokenValidatorFunction(Path keyFilePath){

        final PublicKey publicKey = JWTUtilFRomFile.getPublicKey(keyFilePath);

        return jwtString -> Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(jwtString);
    }

    private HashMap<String, Object> getClaimsMap(){
        return new HashMap<>();
    }

    private static PrivateKey getPrivateKey(Path filePath) throws JWTokenException {
        try {
            PKCS8EncodedKeySpec spec =
                    new PKCS8EncodedKeySpec(getByteArrayFromFile(filePath));
            return getRSAInstanceKeyFactory().generatePrivate(spec);
        } catch (Exception e) {
            throw new JWTokenException(e.getMessage());
        }
    }

    private static PublicKey getPublicKey(Path filePath) throws JWTokenException {
        try {
            X509EncodedKeySpec spec =
                    new X509EncodedKeySpec(getByteArrayFromFile(filePath));
            return getRSAInstanceKeyFactory().generatePublic(spec);
        } catch (Exception e){
            throw new JWTokenException(e.getMessage());
        }
    }

    private static byte[] getByteArrayFromFile(Path filePath) throws IOException {
        return Files.readAllBytes(filePath);
    }

    private static KeyFactory getRSAInstanceKeyFactory() throws Exception{
        return KeyFactory.getInstance("RSA");
    }
}
