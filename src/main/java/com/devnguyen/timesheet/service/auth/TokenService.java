package com.devnguyen.timesheet.service.auth;

import com.devnguyen.timesheet.model.Token;
import com.devnguyen.timesheet.repository.TokenRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService{

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token getByUsername(String username) {
        return tokenRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Not found token"));
    }

    public int save(Token token) {
        Optional<Token> optional = tokenRepository.findByUsername(token.getUsername());

        if (optional.isEmpty())
        {
            tokenRepository.save(token);
            return token.getId();
        }
        else {
            Token existingToken = optional.get();
            existingToken.setAccessToken(token.getAccessToken());
            existingToken.setRefreshToken(token.getRefreshToken());
            tokenRepository.save(existingToken);

            return existingToken.getId();
        }
    }

    public void delete(String username) {
        Token token = getByUsername(username);
        tokenRepository.delete(token);
    }
}