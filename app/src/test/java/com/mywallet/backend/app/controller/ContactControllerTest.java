package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ContactDTO;
import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.service.ContactService;
import com.mywallet.backend.app.utility.AuthUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @Mock
    AuthUtility authUtility;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllContactsByUsername_Success() {
        String username = "testuser";
        Contact contact = new Contact(username);
        String token = authUtility.generateToken(username);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);
        when(contactService.getContactsByUsername(username)).thenReturn(contact);

        ResponseEntity<ResponseDTO> responseEntity = contactController.getAllContactsByUsername(token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Contacts fetched success", responseEntity.getBody().getMessage());
        assertEquals(contact, responseEntity.getBody().getData());
    }

    @Test
    public void testGetAllContactsByUsername_Unauthorized() {
        String token = "invalid_token";
//        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = contactController.getAllContactsByUsername(token);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetAllContactsByUsername_InternalServerError() {
        String token = "valid_token";
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = contactController.getAllContactsByUsername(token);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }
//
    @Test
    public void testAddContact_Success() throws Exception {
        String username = "testuser";
        String token = authUtility.generateToken(username);
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setContact(username);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);
        when(contactService.addContact(username, contactDTO.getContact())).thenReturn(new ResponseDTO(true, "Contact added successfully", null));

        ResponseEntity<ResponseDTO> responseEntity = contactController.addContact(token, contactDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Contact added successfully", responseEntity.getBody().getMessage());
    }
//
    @Test
    public void testAddContact_Unauthorized() throws Exception {
        String token = "invalid_token";
        ContactDTO contactDTO = new ContactDTO();
        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = contactController.addContact(token, contactDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testAddContact_InternalServerError() throws Exception {
        String token = "valid_token";
        String newContact="testuser1";
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setContact(newContact);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn("testuser");
        when(contactService.addContact("testuser", contactDTO.getContact())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = contactController.addContact(token, contactDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }
}
