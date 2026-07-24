package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepo extends JpaRepository<User, String> {
    List<User> findAll();
}
