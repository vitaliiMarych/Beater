package com.example.biter.Repos;

import com.example.biter.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findById(Long id);

    User findByActivationCode(String code);

    User findByEmail(String email);

    void deleteById(Long id);

}
