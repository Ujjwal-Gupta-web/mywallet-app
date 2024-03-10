package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.VerificationDao;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.repository.VerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
public class VerificationService {

    @Autowired
    VerificationDao verificationDao;
    public void add(Verification verification){
        verificationDao.add(verification);
    }
    public Verification getVerificationDetailsByUsername(@PathVariable String username){
        return verificationDao.getVerificationDetailsByUsername(username);
    }

    public boolean chcekValidity(String username) {
        Verification verification=verificationDao.getVerificationDetailsByUsername(username);
        if(verification==null) return false;
        long timeDifferenceMillis = new Date().getTime() - verification.getLastUpdatedAt().getTime();
        long diff=(timeDifferenceMillis/(1000*60));
        return diff<5;
    }
}
