package com.mywallet.backend.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.List;

@Document(collection = "contact")
public class Contact {
    @Id
    @Indexed(unique = true)
    @Field("username")
    private String username;
    private HashSet<String> contactList;

    public Contact(String username) {
        this.username = username;
        this.contactList = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashSet<String> getContactList() {
        return contactList;
    }

    public void setContactList(HashSet<String> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "username='" + username + '\'' +
                ", contactList=" + contactList +
                '}';
    }
}
