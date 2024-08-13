package com.devnguyen.timesheet.filter;

import com.devnguyen.timesheet.service.auth.UserDetailsServiceImp;
import com.devnguyen.timesheet.service.auth.JwtService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.devnguyen.timesheet.enums.TokenType.ACCESS_TOKEN;

@Component
public class PreFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private  UserDetailsServiceImp userDetailsServiceImp;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token, ACCESS_TOKEN);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);

            if (jwtService.isValid(token, ACCESS_TOKEN, userDetails))
            {
                System.out.println("TOKEN VALID");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken
                        .setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            } else
            {
                System.out.println("TOKEN INVALID");
            }
        }
        filterChain.doFilter(request, response);
        
    }

}