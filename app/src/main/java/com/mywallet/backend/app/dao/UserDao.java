package com.mywallet.backend.app.dao;

import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    UserRepo userRepo;


    public void createUser(User user) {
        userRepo.save(user);
    }

    public User getUserByUserName(String username) {
        return userRepo.findById(username).orElse(null);
    }

    public void deleteUser(String username){
        userRepo.deleteById(username);
    }

    public User changePassword(User user) {
        User userdets=userRepo.findById(user.getUsername()).orElse(null);
        if(userdets==null) return null;
        userdets.setPassword(user.getPassword());
        userRepo.save(userdets);
        return userdets;
    }
}
