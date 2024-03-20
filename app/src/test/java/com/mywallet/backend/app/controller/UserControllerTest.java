package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.dto.UserDTO;
import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.service.UserService;
import com.mywallet.backend.app.service.VerificationService;
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

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private VerificationService verificationService;
    
    @Mock
    private AuthUtility authUtility;
    
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        User user = new User("testuser", "password");
        when(userService.getUserByUserName("testuser")).thenReturn(null);
        when(verificationService.chcekValidity("testuser")).thenReturn(true);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<ResponseDTO> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created success", responseEntity.getBody().getMessage());
    }

    @Test
    public void testCreateUser_UserExists() {
        User user = new User("existinguser", "password");
        when(userService.getUserByUserName("existinguser")).thenReturn(user);

        ResponseEntity<ResponseDTO> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User already exists", responseEntity.getBody().getMessage());
    }

    @Test
    public void testCreateUser_VerificationFailure() {
        User user = new User("testuser", "password");
        when(userService.getUserByUserName("testuser")).thenReturn(null);
        when(verificationService.chcekValidity("testuser")).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Verification failed, try later", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLoginUser_Success() {
        User user = new User("testuser", "password");
        when(userService.loginUser(user)).thenReturn(user);
        when(authUtility.generateToken("testuser")).thenReturn("token");

        ResponseEntity<ResponseDTO> responseEntity = userController.loginUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login Success", responseEntity.getBody().getMessage());
        assertEquals("token", ((UserDTO) responseEntity.getBody().getData()).getToken());
    }

    @Test
    public void testLoginUser_UserNotFound() {
        User user = new User("nonexistentuser", "password");
        when(userService.loginUser(user)).thenReturn(null);

        ResponseEntity<ResponseDTO> responseEntity = userController.loginUser(user);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("USER NOT FOUND", responseEntity.getBody().getMessage());
    }

    @Test
    public void testDeleteUser_Success() {
        String username = "testuser";
        String token = authUtility.generateToken(username);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);

        ResponseEntity<ResponseDTO> responseEntity = userController.deleteUser(token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User Deleted Success", responseEntity.getBody().getMessage());
    }

    @Test
    public void testDeleteUser_Unauthorized() {
        String token = "invalid_token";
        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = userController.deleteUser(token);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testChangePassword_Success() {
        User user = new User("testuser", "new_password");
        when(verificationService.chcekValidity("testuser")).thenReturn(true);
        when(userService.changePassword(user)).thenReturn(user);

        ResponseEntity<ResponseDTO> responseEntity = userController.changePassword(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Password Update Success", responseEntity.getBody().getMessage());
    }

    @Test
    public void testChangePassword_VerificationFailure() {
        User user = new User("testuser", "new_password");
        when(verificationService.chcekValidity("testuser")).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = userController.changePassword(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Verification failed, try later", responseEntity.getBody().getMessage());
    }

    @Test
    public void testInternalServerError() {
        User user = new User("testuser", "password");
        when(userService.getUserByUserName("testuser")).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }
}
