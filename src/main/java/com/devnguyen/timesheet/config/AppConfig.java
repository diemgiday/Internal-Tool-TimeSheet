package com.devnguyen.timesheet.config;


import com.devnguyen.timesheet.filter.PreFilter;
import com.devnguyen.timesheet.service.auth.UserDetailsServiceImp;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class AppConfig {
    @Autowired
    private PreFilter preFilter;
    @Autowired
    private  UserDetailsServiceImp userDetailsServiceImp;

    private final String[] WHITE_LIST = {"/auth/**", "/mail/**","/scheduler/**"};

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET, POST, PUT, DELETE") // Allowed HTTP methods
                        .allowedHeaders("*") // Allowed request headers
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(@NonNull HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        //request khong can token
                        .requestMatchers(WHITE_LIST)
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/branches/**", "/positions/**","/timesheet/**")
                        .hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/branches/**", "/positions/**","/timesheet/**" )
                        .hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                        .requestMatchers(HttpMethod.PUT,"/branches/**", "/positions/**","/timesheet/**" )
                        .hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.DELETE,"/branches/**", "/positions/**","/timesheet/**" )
                        .hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")

                        .anyRequest()
                        .authenticated())

                //khong luu token tren server
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(provider())
                .addFilterBefore(preFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsServiceImp);

        provider.setPasswordEncoder(getPasswordEncoder());

        return provider;
    }
}

