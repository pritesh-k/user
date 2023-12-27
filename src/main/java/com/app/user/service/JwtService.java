package com.app.user.service;

import com.app.user.model.data.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    Map<String, String> generateToken(User user);

    String extractUsername(String token);

    Boolean validateToken(String token, UserDetails userDetails);
}
