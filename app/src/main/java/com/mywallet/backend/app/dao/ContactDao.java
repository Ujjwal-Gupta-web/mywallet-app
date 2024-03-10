package com.mywallet.backend.app.dao;

import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactDao {
    @Autowired
    ContactRepo contactRepo;

    public Contact getContactsByUsername(String username) {
        return contactRepo.findById(username).orElse(null);
    }

    public void addContact(Contact contact) {

        System.out.println(contactRepo.save(contact));
    }
}
