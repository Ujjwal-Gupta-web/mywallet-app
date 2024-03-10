package com.mywallet.backend.app.utility;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OTPUtilityTest {

    @Test
    void testGenerateOTP() {
        String otp = OTPUtility.generateOTP();

        assertNotNull(otp);
        assertEquals(6, otp.length());

        for (char c : otp.toCharArray()) {
            assertTrue(Character.isDigit(c));
        }
    }

    @RepeatedTest(10)
    void testGenerateOTP_Unique() {
        String otp1 = OTPUtility.generateOTP();
        String otp2 = OTPUtility.generateOTP();

        assertNotEquals(otp1, otp2);
    }
}
