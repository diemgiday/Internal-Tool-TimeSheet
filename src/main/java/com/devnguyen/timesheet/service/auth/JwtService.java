package com.devnguyen.timesheet.service.auth;

import com.devnguyen.timesheet.enums.TokenType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {


    String generateToken(UserDetails user);

    String generateRefreshToken(UserDetails user);

    String generateResetToken(UserDetails user);

    String extractUsername(String token, TokenType type);

    boolean isValid(String token, TokenType type, UserDetails user);


}
