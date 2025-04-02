package com.example.sneaker.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sneaker.entities.Brand;
import com.example.sneaker.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
	boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
    Optional<User> findByUsername(String username);
}
