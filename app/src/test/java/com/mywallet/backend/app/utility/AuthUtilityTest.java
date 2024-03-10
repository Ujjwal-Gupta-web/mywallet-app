package com.mywallet.backend.app.utility;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthUtilityTest {

    @Test
    void testGenerateToken() {
        String username = "testUser";
        String token = AuthUtility.generateToken(username);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testIsValidToken_ValidToken() {
        String username = "testUser";
        String token = AuthUtility.generateToken(username);

        assertTrue(AuthUtility.isValidToken(token));
    }

    @Test
    void testIsValidToken_ExpiredToken() {
        String username = "testUser";
        String expiredToken = AuthUtility.generateToken(username);

        // Manipulate the token expiration time to make it expired
        String modifiedToken = expiredToken.substring(0, expiredToken.length() - 1);

        assertFalse(AuthUtility.isValidToken(modifiedToken));
    }

    @Test
    void testIsValidToken_TamperedToken() {
        String username = "testUser";
        String token = AuthUtility.generateToken(username);

        // Tamper the token by appending some characters
        String tamperedToken = token + "abc";

        assertFalse(AuthUtility.isValidToken(tamperedToken));
    }

    @Test
    void testGetUsernameFromToken_ValidToken() {
        String username = "testUser";
        String token = AuthUtility.generateToken(username);

        assertEquals(username, AuthUtility.getUsernameFromToken(token));
    }

    @Test
    void testGetUsernameFromToken_ExpiredToken() {
        String username = "testUser";
        String expiredToken = AuthUtility.generateToken(username);

        // Manipulate the token expiration time to make it expired
        String modifiedToken = expiredToken.substring(0, expiredToken.length() - 1);

        assertNull(AuthUtility.getUsernameFromToken(modifiedToken));
    }

    @Test
    void testGetUsernameFromToken_TamperedToken() {
        String username = "testUser";
        String token = AuthUtility.generateToken(username);

        // Tamper the token by appending some characters
        String tamperedToken = token + "abc";

        assertNull(AuthUtility.getUsernameFromToken(tamperedToken));
    }
}

