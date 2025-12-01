package com.example.jwt_auth_with_authorisation.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET="super-secret-key-ireuwiurweqyiuerwqyyiuerw";
    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long EXPIRARTION_TIME=1000*60*60;
    public String generateToken(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRARTION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token)
    {
        Claims claims =extractClaims(token);
        return claims.getSubject();
    }


    //a method to get the claims object for various opearations
    public Claims extractClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateUsernames(UserDetails userDetails, String username, String token) {
       return  userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token)
    {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
