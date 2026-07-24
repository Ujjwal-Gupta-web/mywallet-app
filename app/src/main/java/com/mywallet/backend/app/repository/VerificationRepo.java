package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRepo extends JpaRepository<Verification, String> {
    List<Verification> findAll();
}
