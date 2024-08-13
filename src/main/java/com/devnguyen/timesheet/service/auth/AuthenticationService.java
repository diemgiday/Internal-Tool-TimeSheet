package com.devnguyen.timesheet.service.auth;

import com.devnguyen.timesheet.dto.request.AccountRequest;
import com.devnguyen.timesheet.dto.request.RequestPasswordDTO;
import com.devnguyen.timesheet.dto.request.SignInRequest;
import com.devnguyen.timesheet.dto.response.TokenResponse;
import com.devnguyen.timesheet.mapper.UserMapper;
import com.devnguyen.timesheet.model.Token;
import com.devnguyen.timesheet.model.User;
import com.devnguyen.timesheet.repository.UserRepository;
import com.devnguyen.timesheet.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.devnguyen.timesheet.enums.TokenType.ACCESS_TOKEN;
import static com.devnguyen.timesheet.enums.TokenType.REFRESH_TOKEN;
import static com.devnguyen.timesheet.enums.TokenType.RESET_TOKEN;
import static org.springframework.http.HttpHeaders.REFERER;

import java.util.Optional;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final UserService userService;


    public void register(AccountRequest accountRequest){
        User user = UserMapper.INSTANCE.toEntity(accountRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Account Verification");
        message.setText("Please verify your account by clicking the link: " );
        mailSender.send(message);
    }

    public TokenResponse authenticate(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUserName(),
                signInRequest.getPassWord()));

       var user = userRepository.findByUsername(signInRequest.getUserName()).orElseThrow(()
               -> new UsernameNotFoundException("Username or Password is incorrect"));

       String accessToken = jwtService.generateToken(user);
       String refreshToken = jwtService.generateRefreshToken(user);

       //save token to db
       tokenService.save(
               Token.builder()
               .username(user.getUsername())
               .accessToken(accessToken)
               .refreshToken(refreshToken)
               .build());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userName(user.getUsername())
                .build();
    }

    public TokenResponse refreshToken(HttpServletRequest request) {
        log.info("---------- refreshToken ----------");
        final String refreshToken = request.getHeader(REFERER);

        //check db
        final String userName = jwtService.extractUsername(refreshToken, REFRESH_TOKEN);
        var user = userRepository.findByUsername(userName);

        // create new access token
        String accessToken = jwtService.generateToken(user.get());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userName(user.get().getUsername())
                .build();
    }
    public String logout(HttpServletRequest request) {
        log.info("---------- logout ----------");

        final String token = request.getHeader(REFERER);
        final String userName = jwtService.extractUsername(token, ACCESS_TOKEN);

        tokenService.delete(userName);

        return "Logouted !!!";
    }

    public String forgotPassword(String email) {
        // check email 
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    
        // generate reset token
        String resetToken = jwtService.generateResetToken(user);
    
        // Create the reset link
        String confirmLink = "http://localhost:8080/auth/reset-password?token=" + resetToken;
    
        // send email confirmation
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Forgot Password");
        message.setText("Please confirm to reset your password by clicking the link: " + confirmLink);
        mailSender.send(message);
    
        return "Password reset email sent successfully.";
    }
    

    public String resetPassword(String secretKey) {
        log.info("-----------------reset password -------------------");

        final String userName = jwtService.extractUsername(secretKey, RESET_TOKEN);

        @SuppressWarnings("unused")
        var user = userRepository.findByUsername(userName);

        return "Reset PassWord Successfull !";
    }

    public String changePassword(RequestPasswordDTO request) {
        
        User user = isValidUserByToken(request.getSecretKey());

        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new InvalidDataAccessApiUsageException("password not match !");
        } 
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userService.updateUser(user);

        return "changed password successfull";
    }

    public User isValidUserByToken(String secretKey) {
        
        final String userName = jwtService.extractUsername(secretKey, RESET_TOKEN);

        Optional<User> userOptional = userRepository.findByUsername(userName);

        // Kiểm tra nếu người dùng tồn tại, nếu không ném ngoại lệ
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
}

}