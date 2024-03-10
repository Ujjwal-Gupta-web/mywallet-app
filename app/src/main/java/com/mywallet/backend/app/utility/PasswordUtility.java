package com.mywallet.backend.app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtility {

    public String generateHash(String password) {
        return password;
    }
    public static boolean verifyHash(String username,String hashedPassword) {
        return true;
    }
}
