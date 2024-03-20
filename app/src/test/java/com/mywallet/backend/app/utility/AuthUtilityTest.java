package com.mywallet.backend.app.utility;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AuthUtilityTest {

    @Test
    void testGenerateToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String token = authUtility.generateToken(username);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testIsValidToken_ValidToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String token = authUtility.generateToken(username);

        assertTrue(authUtility.isValidToken(token));
    }

    @Test
    void testIsValidToken_ExpiredToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String expiredToken = authUtility.generateToken(username);

        // Manipulate the token expiration time to make it expired
        String modifiedToken = expiredToken.substring(0, expiredToken.length() - 1);

        assertFalse(authUtility.isValidToken(modifiedToken));
    }

    @Test
    void testIsValidToken_TamperedToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String token = authUtility.generateToken(username);

        // Tamper the token by appending some characters
        String tamperedToken = token + "abc";

        assertFalse(authUtility.isValidToken(tamperedToken));
    }

    @Test
    void testGetUsernameFromToken_ValidToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String token = authUtility.generateToken(username);

        assertEquals(username, authUtility.getUsernameFromToken(token));
    }

    @Test
    void testGetUsernameFromToken_ExpiredToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String expiredToken = authUtility.generateToken(username);

        // Manipulate the token expiration time to make it expired
        String modifiedToken = expiredToken.substring(0, expiredToken.length() - 1);

        assertNull(authUtility.getUsernameFromToken(modifiedToken));
    }

    @Test
    void testGetUsernameFromToken_TamperedToken() {
        AuthUtility authUtility=new AuthUtility();
        String username = "testUser";
        String token = authUtility.generateToken(username);

        // Tamper the token by appending some characters
        String tamperedToken = token + "abc";

        assertNull(authUtility.getUsernameFromToken(tamperedToken));
    }
}

