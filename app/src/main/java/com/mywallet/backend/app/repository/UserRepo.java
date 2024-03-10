package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAll();
}
