package com.mywallet.backend.app.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "contact_entries", joinColumns = @JoinColumn(name = "username"))
    @Column(name = "contact")
    private Set<String> contactList;

    protected Contact() {
    }

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

    public Set<String> getContactList() {
        return contactList;
    }

    public void setContactList(Set<String> contactList) {
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
