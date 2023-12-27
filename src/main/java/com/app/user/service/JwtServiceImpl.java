package com.app.user.service;

import com.app.user.config.ServerConfig;
import com.app.user.model.data.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtServiceImpl implements JwtService {

    @Autowired
    ServerConfig serverConfig;
    @Value("${app.jwttoken.message}")
    private String message;
    @Value("${app.jwttoken.expirationTime}")
    private long ACCESS_TOKEN_VALIDITY_SECONDS;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken="";
        Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .add("email", user.getEmail())
                .add("userId", String.valueOf(user.getId()))
                .add("roles", "USER")
                .build();
        jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
        extractClaim(jwtToken);
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }

    public String extractUsername(String token){
        return extractClaim(token).getSubject();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(serverConfig.getJwtSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token).getExpiration();
    }

    public Claims extractClaim(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
