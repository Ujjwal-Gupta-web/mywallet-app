package com.mywallet.backend.app.service;

import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.utility.EmailUtility;
import com.mywallet.backend.app.utility.OTPUtility;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class EmailService {

    @Autowired
    EmailUtility emailUtility;

    @Autowired
    VerificationService verificationService;

    //match pattern
    // generate random 6 digit otp
    // send to given email
    //The OTP for account verification is {OTP}
    // send the otp in the response of the service also
    // save that otp in localStorage.
//        System.out.println("THIS is reaceived : "+email);
    public String sendOTP(String email) {
        boolean isValid=EmailUtility.isValidEmail(email);
        if(isValid){
            String otp= OTPUtility.generateOTP();
            Verification verification=new Verification(email,otp,new Date());
            verificationService.add(verification);
            String message="OTP : "+otp;
            String subject="OTP from MyWallet";
            boolean status=emailUtility.sendMail(email,subject, message);
            return status?otp.toString():"";
        }
        return "";
    }


    public void sendTransactionUpdate(String username, Transaction transaction) {
        String email=username;
        String subject=transaction.getTransactionType() + " : " + transaction.getAmount() + " - " + transaction.getTransactionWith();
        String body=transaction.toString();
        emailUtility.sendMail(email,subject,body);
    }

    public void sendCashBackUnloacked(String username) {
        String email=username;
        String subject="Congratulations! Cashback Unlocked on next wallet recharge";
        String body="Dear "+username+", you unloacked a cashback for you next wallet recharge" +
                "\nMyWallet thanks you for being a valued member.";
        emailUtility.sendMail(email,subject,body);
    }
}
