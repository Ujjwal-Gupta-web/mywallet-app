package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepo extends MongoRepository<Contact, String> {
    List<Contact> findAll();
}
