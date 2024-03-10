package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.service.EmailService;
import com.mywallet.backend.app.utility.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @GetMapping("/sendOTP/{email}")
    public ResponseEntity<ResponseDTO> sendOTP(@PathVariable String email){
        try{
            String otp=emailService.sendOTP(email);
            if(otp==""){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(false,"Please check your credentials once",null));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(true,"OTP Sent Success",otp));
        }
         catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong",e));
         }
    }

}
