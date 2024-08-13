package com.devnguyen.timesheet.controller.auth;


import com.devnguyen.timesheet.dto.request.AccountRequest;
import com.devnguyen.timesheet.dto.request.RequestPasswordDTO;
import com.devnguyen.timesheet.dto.request.SignInRequest;
import com.devnguyen.timesheet.dto.response.TokenResponse;
import com.devnguyen.timesheet.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountRequest accountRequest) {
        authenticationService.register(accountRequest);
        return ResponseEntity.ok("Registration successful. Please check your email to verify your account.");
    }
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/access-token")
    public ResponseEntity login (@Valid @RequestBody SignInRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.refreshToken(request), OK);
    }

    @PostMapping("/remove-token")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.logout(request), OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        return new ResponseEntity<>(authenticationService.forgotPassword(email), OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody String secretKey) {
        return new ResponseEntity<>(authenticationService.resetPassword(secretKey), OK);
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody RequestPasswordDTO request) {
        return new ResponseEntity<>(authenticationService.changePassword(request), OK);
    }

}