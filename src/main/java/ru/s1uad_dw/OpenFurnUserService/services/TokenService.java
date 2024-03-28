package ru.s1uad_dw.OpenFurnUserService.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.s1uad_dw.OpenFurnUserService.exceptions.InvalidDataException;
import ru.s1uad_dw.OpenFurnUserService.exceptions.TokenExpiredException;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {
    @Value("${secret-key}")
    private String secretKey;
    public void checkTokenExpiration(String token){
        try {
            Claims claims = getTokenBody(token);
            Date expiration = claims.getExpiration();
            if (!expiration.before(new Date())){
                throw new TokenExpiredException("Token expired");
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidDataException("Invalid token");
        }
    }

    public UUID getId(String token){
        Claims claims = getTokenBody(token);
        return UUID.fromString(claims.getSubject());
    }

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
