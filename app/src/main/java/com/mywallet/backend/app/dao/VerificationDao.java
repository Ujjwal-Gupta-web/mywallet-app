package com.mywallet.backend.app.dao;

import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.repository.VerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationDao {

    @Autowired
    VerificationRepo verificationRepo;
    public void add(Verification verification) {
        Verification verificationDets=verificationRepo.findById(verification.getUsername()).orElse(null);
        if(verificationDets!=null){
            verificationDets.setOtp(verification.getOtp());
            verificationDets.setLastUpdatedAt(verification.getLastUpdatedAt());
            verificationRepo.save(verificationDets);
        }
        else{
            verificationRepo.save(verification);
        }

    }

    public Verification getVerificationDetailsByUsername(String username) {
        return verificationRepo.findById(username).orElse(null);
    }
}
