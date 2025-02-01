package com.sonnguyen.base.utils;

import com.sonnguyen.base.dto.request.IntrospectRequest;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateToken(IntrospectRequest request) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(request.getToken());

            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token");
        } catch (SignatureException e) {
            System.out.println("Invalid token signature");
        } catch (Exception e) {
            System.out.println("Token validation failed");
        }
        return false;
    }
}
