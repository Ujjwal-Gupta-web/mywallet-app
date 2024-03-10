package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.models.Verification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VerificationRepo extends MongoRepository<Verification, String> {
    List<Verification> findAll();
}

