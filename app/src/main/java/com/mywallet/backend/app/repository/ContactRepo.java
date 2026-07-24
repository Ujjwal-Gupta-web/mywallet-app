package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, String> {
    List<Contact> findAll();
}
