package com.itschool.blog.repository;

import com.itschool.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email); // Retrieve a user by email
    Optional<User> findByUsernameOrEmail(String username, String email); // Retrieve a user by username or email
    Optional<User> findByUsername(String username);  // Retrieve a user by username
    Boolean existsByUsername(String username); // Check if the user exists by username
    Boolean existsByEmail(String email); // Check if the user exists by email
}
