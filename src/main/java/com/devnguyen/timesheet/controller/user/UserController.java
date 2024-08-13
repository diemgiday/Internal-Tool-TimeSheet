package com.devnguyen.timesheet.controller.user;

import com.devnguyen.timesheet.model.User;
import com.devnguyen.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("/all")
        public Page<User> getUsers(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "10") int size,
                @RequestParam(value = "sortBy", defaultValue = "username") String sortBy) {

                return userService.getUsers(page, size, sortBy);
        }

        @GetMapping("/{id}")
        public Optional<User> getUserById(@PathVariable int id) {
                return userService.getUserById(id);
        }

        @PostMapping("/add")
        public void addUser(@RequestBody User user) {
                userService.addUser(user);
        }

        @PutMapping("/update")
        public ResponseEntity<?> updateUser(@RequestBody User user) {

                Optional <User> existUser = userService.getUserById(user.getUserId());

                if (existUser.isPresent()) {
                        userService.updateUser(user);
                        return ResponseEntity.ok().build();
                }
                else {
                        return ResponseEntity.notFound().build();
                }

        }

        @DeleteMapping("/delete/{id}")
        public void deleteUser(@PathVariable int id ) {
                userService.deleteUser(id);
        }


}













