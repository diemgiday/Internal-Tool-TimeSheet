package com.devnguyen.timesheet.service.auth;

import com.devnguyen.timesheet.model.User;
import com.devnguyen.timesheet.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(userName);

        try {
            if (user.isPresent()) {
                System.out.println(user.get().getUsername());
            } else {
                throw new UsernameNotFoundException(userName);
            }
            return repository.findByUsername(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

}