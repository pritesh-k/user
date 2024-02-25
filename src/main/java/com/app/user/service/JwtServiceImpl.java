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

    /**
     * Generates a JWT token for the given user.
     *
     * @param user The user for whom the token is generated.
     * @return A Map containing the generated JWT token and a message.
     */
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";
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

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token).getSubject();
    }

    /**
     * Retrieves the signing key used for token validation.
     *
     * @return The signing key.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(serverConfig.getJwtSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token).getExpiration();
    }

    /**
     * Extracts the claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted claims.
     */
    public Claims extractClaim(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SecurityException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates the JWT token against the provided UserDetails.
     *
     * @param token       The JWT token.
     * @param userDetails The UserDetails object.
     * @return True if the token is valid for the UserDetails, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
