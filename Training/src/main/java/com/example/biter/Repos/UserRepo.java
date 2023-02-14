package com.example.biter.Repos;

import com.example.biter.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(int id);

    User findByActivationCode(String code);

}
