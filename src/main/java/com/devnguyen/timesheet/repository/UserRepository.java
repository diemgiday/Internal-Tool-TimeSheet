package com.devnguyen.timesheet.repository;


import com.devnguyen.timesheet.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional <User> findByUsername(String username);
    
    User findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.username ASC")
    Page<User> findAllUsers(Pageable pageable);

}
