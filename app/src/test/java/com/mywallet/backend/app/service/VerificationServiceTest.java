package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.VerificationDao;
import com.mywallet.backend.app.models.Verification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificationServiceTest {

    @Mock
    private VerificationDao verificationDao;

    @InjectMocks
    private VerificationService verificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddVerification() {
        Verification verification = new Verification("testUser","12345",new Date());
        verificationService.add(verification);

        verify(verificationDao, times(1)).add(verification);
    }

    @Test
    void testGetVerificationDetailsByUsername() {
        String username = "testUser";
        Verification verification = new Verification("testUser","12345",new Date());

        when(verificationDao.getVerificationDetailsByUsername(username)).thenReturn(verification);

        Verification retrievedVerification = verificationService.getVerificationDetailsByUsername(username);

        assertNotNull(retrievedVerification);
        assertEquals(verification, retrievedVerification);

        verify(verificationDao, times(1)).getVerificationDetailsByUsername(username);
    }

    @Test
    void testCheckValidity_ValidVerification() {
        String username = "testUser";
        Verification verification = new Verification("testUser","12345",new Date());
        verification.setLastUpdatedAt(new Date(System.currentTimeMillis() - 4 * 60 * 1000)); // Setting the last update within 4 minutes

        when(verificationDao.getVerificationDetailsByUsername(username)).thenReturn(verification);

        boolean isValid = verificationService.chcekValidity(username);

        assertTrue(isValid);

        verify(verificationDao, times(1)).getVerificationDetailsByUsername(username);
    }

    @Test
    void testCheckValidity_InvalidVerification() {
        String username = "testUser";
        Verification verification = new Verification("testUser","12345",new Date());
        verification.setLastUpdatedAt(new Date(System.currentTimeMillis() - 6 * 60 * 1000)); // Setting the last update beyond 5 minutes

        when(verificationDao.getVerificationDetailsByUsername(username)).thenReturn(verification);

        boolean isValid = verificationService.chcekValidity(username);

        assertFalse(isValid);

        verify(verificationDao, times(1)).getVerificationDetailsByUsername(username);
    }

    @Test
    void testCheckValidity_NoVerification() {
        String username = "testUser";

        when(verificationDao.getVerificationDetailsByUsername(username)).thenReturn(null);

        boolean isValid = verificationService.chcekValidity(username);

        assertFalse(isValid);

        verify(verificationDao, times(1)).getVerificationDetailsByUsername(username);
    }
}
