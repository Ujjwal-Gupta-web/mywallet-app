package com.mywallet.backend.app.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmailUtilityTest {

    @Autowired
    private EmailUtility emailUtility;

    @MockBean
    private JavaMailSender javaMailSender;

    @Test
    void testIsValidEmail_ValidEmail() {
        String validEmail = "test@example.com";
        assertTrue(EmailUtility.isValidEmail(validEmail));
    }

    @Test
    void testIsValidEmail_InvalidEmail() {
        String invalidEmail = "invalid-email";
        assertFalse(EmailUtility.isValidEmail(invalidEmail));
    }

//    @Test
//    void testSendMail_Success() {
//        String to = "recipient@example.com";
//        String subject = "Test Subject";
//        String body = "Test Body";
//
//        when(javaMailSender.send(any())).thenReturn(null);
//
//        assertTrue(emailUtility.sendMail(to, subject, body));
//
//        verify(javaMailSender, times(1)).send(any());
//    }
//
//    @Test
//    void testSendMail_Failure() {
//        String to = "recipient@example.com";
//        String subject = "Test Subject";
//        String body = "Test Body";
//
//        when(javaMailSender.send(any())).thenThrow(new RuntimeException("Mail sending failed"));
//
//        assertFalse(emailUtility.sendMail(to, subject, body));
//
//        verify(javaMailSender, times(1)).send(any());
//    }
}
