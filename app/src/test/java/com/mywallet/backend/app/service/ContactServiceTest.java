package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.ContactDao;
import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ContactDao contactDao;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddContact_UserNotAvailable() {
        String username = "testUser";
        String contactUsername = "nonExistingUser";

        when(userService.getUserByUserName(contactUsername)).thenReturn(null);

        ResponseDTO responseDTO = contactService.addContact(username, contactUsername);

        
        assertEquals("USER NOT AVAILABLE", responseDTO.getMessage());
        assertNull(responseDTO.getData());

        verify(contactDao, never()).addContact(any());
    }

    @Test
    void testAddContact_UserAlreadyExists() {
        String username = "testUser";
        String contactUsername = "existingUser";
        User existingUser = new User("existingUser","password");
        Contact testUserContact=new Contact(username);
        HashSet<String> testUserContactList=new HashSet<String>();
        testUserContactList.add(contactUsername);
        testUserContact.setContactList(testUserContactList);
        when(userService.getUserByUserName(contactUsername)).thenReturn(existingUser);
        when(contactDao.getContactsByUsername(username)).thenReturn(testUserContact);

        ResponseDTO responseDTO = contactService.addContact(username, contactUsername);

        
        assertEquals("USER ALREADY EXISTS", responseDTO.getMessage());
        assertNull(responseDTO.getData());

        verify(contactDao, never()).addContact(any());
    }

    @Test
    void testAddContact_Success() {
        String username = "testUser";
        String contactUsername = "newUser";
        User newUser = new User("newUser","password");

        when(userService.getUserByUserName(contactUsername)).thenReturn(newUser);
        when(contactDao.getContactsByUsername(username)).thenReturn(null);

        ResponseDTO responseDTO = contactService.addContact(username, contactUsername);
        assertEquals("USER ADDED", responseDTO.getMessage());
        assertNull(responseDTO.getData());

        verify(contactDao, times(1)).addContact(any(Contact.class));
    }

    @Test
    void testGetContactsByUsername() {
        String username = "testUser";
        Contact contact = new Contact(username);

        when(contactDao.getContactsByUsername(username)).thenReturn(contact);

        Contact retrievedContact = contactService.getContactsByUsername(username);

        assertNotNull(retrievedContact);
        assertEquals(username, retrievedContact.getUsername());

        verify(contactDao, times(1)).getContactsByUsername(username);
    }
}
