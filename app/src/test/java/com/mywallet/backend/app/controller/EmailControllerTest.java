package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendOTP_Success() {
        String email = "test@example.com";
        String otp = "123456";

        when(emailService.sendOTP(email)).thenReturn(otp);

        ResponseEntity<ResponseDTO> responseEntity = emailController.sendOTP(email);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("OTP Sent Success", responseEntity.getBody().getMessage());
        assertEquals(otp, responseEntity.getBody().getData());
    }

    @Test
    public void testSendOTP_EmptyOTP() {
        String email = "test@example.com";

        when(emailService.sendOTP(email)).thenReturn("");

        ResponseEntity<ResponseDTO> responseEntity = emailController.sendOTP(email);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please check your credentials once", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
    }

    @Test
    public void testSendOTP_InternalServerError() {
        String email = "test@example.com";

        when(emailService.sendOTP(email)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = emailController.sendOTP(email);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
        assertEquals("Internal Server Error", ((RuntimeException) responseEntity.getBody().getData()).getMessage());
    }
}
