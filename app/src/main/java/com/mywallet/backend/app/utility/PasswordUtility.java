package com.mywallet.backend.app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtility {

    public String generateHash(String password) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public boolean verifyHash(String plainPassword,String hashedPassword) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.matches(plainPassword,hashedPassword);
    }

}
