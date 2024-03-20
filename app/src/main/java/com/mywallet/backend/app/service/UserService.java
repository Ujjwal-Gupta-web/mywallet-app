package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.UserDao;
import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    PasswordUtility passwordUtility;

    public User getUserByUserName(String username){return userDao.getUserByUserName(username);}
    public User createUser(User user) {
        String hashedPassword= passwordUtility.generateHash(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.createUser(user);
        return null;
    }

    public User loginUser(User user){
        User userDets=userDao.getUserByUserName(user.getUsername());
        if(userDets==null) return null;
        String plainPassword=user.getPassword();
        String encryptedPassword=userDets.getPassword();
        if(passwordUtility.verifyHash(plainPassword,encryptedPassword)){
            //generate token and send back that token
            System.out.println("login success");
            return user;
        }
        return null;
    }

    public void deleteUser(String username){
        userDao.deleteUser(username);
    }

    public User changePassword(User user) {
        return userDao.changePassword(user);
    }

//    //if currenttime-lastUpdateTime in verification is less than 5min then go ahead
//    public void changePassword()
}
