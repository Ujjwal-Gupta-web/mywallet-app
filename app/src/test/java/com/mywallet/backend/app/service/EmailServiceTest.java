package com.mywallet.backend.app.service;

import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.utility.EmailUtility;
import com.mywallet.backend.app.utility.OTPUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private EmailUtility emailUtility;

    @Mock
    private VerificationService verificationService;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendTransactionUpdate() {
        String username = "testUser";
        Transaction transaction = new Transaction("123", TransactionType.MONEY_ADDED,"user2",100.0);
        emailService.sendTransactionUpdate(username, transaction);

        verify(emailUtility, times(1)).sendMail(eq(username), anyString(), anyString());
    }

    @Test
    void testSendCashBackUnloacked() {
        String username = "testUser";

        emailService.sendCashBackUnloacked(username);

        verify(emailUtility, times(1)).sendMail(eq(username), anyString(), anyString());
    }
}
