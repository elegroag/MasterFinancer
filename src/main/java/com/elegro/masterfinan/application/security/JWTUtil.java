package com.elegro.masterfinan.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.UnknownClassException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String generateToken(UserDetails userDetails) {
        try {
            return Jwts.builder()
                    .setIssuer("masterfinancer")
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((System.currentTimeMillis() +  1000) * 60 * 60 * 2))
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact();
        } catch (UnknownClassException err){
            return err.getMessage();
        }
     }

    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extraUsername(token)) && !isTokenExpired(token);
    }

    public String extraUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
