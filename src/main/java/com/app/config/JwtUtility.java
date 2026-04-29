package com.app.config;

import com.app.dto.LoginRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    private static final String SECRET = "osohjejrilndi3876373hjbu437970hej8y4";

    public SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .signWith(getKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .compact();

    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserName(String token){
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        return extractClaims(token).getExpiration().after(new Date());
    }

}
