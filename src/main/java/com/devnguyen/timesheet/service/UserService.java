package com.devnguyen.timesheet.service;

import com.devnguyen.timesheet.model.User;
import com.devnguyen.timesheet.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService  {

        @Autowired
        private  UserRepository userRepository;

        public Page<User> getUsers(int page, int size, String sortBy) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            return userRepository.findAllUsers(pageable);
    }
        public Optional<User> getUserById(int id) {
            return userRepository.findById(id);
        }

        public void addUser(User user) {
            userRepository.save(user);
        }

        public void updateUser(User user) {
            User newUser = userRepository.save(user);
            userRepository.save(newUser);
        }

        public void deleteUser(int id) {
            userRepository.deleteById(id);
        }



}

