package ru.itis.healthserviceimpl.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.healthserviceimpl.security.property.JwtProperty;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;

    public String createToken(Map<String, Object> data){
        Date now = new Date();
        Date expireDate = Date.from(Instant.now().plusMillis(jwtProperty.getExpirationAccessInMills()));
        Claims claims = Jwts.claims(data);
        Key key = getSecretKey();
        return Jwts.builder()
                .setExpiration(expireDate)
                .setIssuedAt(now)
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    public Claims parseToken(String token){
        if (!isValidToken(token)){
            return Jwts.claims();
        }
        Key secretKey = getSecretKey();
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        return claims.getBody();
    }

    private boolean isValidToken(String token){
        try {
            Key secretKey = getSecretKey();
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException |
                 UnsupportedJwtException |
                 MalformedJwtException |
                 SignatureException |
                 IllegalArgumentException e) {
            return false;
        }
    }

    private Key getSecretKey(){
        return new SecretKeySpec(jwtProperty.getJwtSecret().getBytes(), "HmacSHA512");
    }
}
