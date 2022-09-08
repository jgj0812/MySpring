package com.example.springboot.study.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// D031
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

