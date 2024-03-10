package com.mywallet.backend.app.utility;

import java.util.Random;

public class OTPUtility {
    private static final String NUMBERS = "0123456789";

    public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        return otp.toString();
    }
}
