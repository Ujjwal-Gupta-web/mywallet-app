package com.mywallet.backend.app.dao;

import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.repository.AccountStatementRepo;
import com.mywallet.backend.app.repository.ContactRepo;
import com.mywallet.backend.app.repository.UserRepo;
import com.mywallet.backend.app.repository.VerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class UserDao {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AccountStatementRepo accountStatementRepo;

    @Autowired
    VerificationRepo verificationRepo;
    @Autowired
    ContactRepo contactRepo;
    public void createUser(User user) {
        userRepo.save(user);
        accountStatementRepo.save(new AccountStatement(user.getUsername(),new ArrayList<>(),0.0));
        contactRepo.save(new Contact(user.getUsername()));
    }

    public User getUserByUserName(String username) {
        return userRepo.findById(username).orElse(null);
    }

    public void deleteUser(String username){
        userRepo.deleteById(username);
        accountStatementRepo.deleteById(username);
        contactRepo.deleteById(username);
        verificationRepo.deleteById(username);
    }

    public User changePassword(User user) {
        User userdets=userRepo.findById(user.getUsername()).orElse(null);
        if(userdets==null) return null;
        userdets.setPassword(user.getPassword());
        userRepo.save(userdets);
        return userdets;
    }
}
