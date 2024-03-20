package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.dto.UserDTO;
import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.models.Verification;
import com.mywallet.backend.app.service.UserService;
import com.mywallet.backend.app.service.VerificationService;
import com.mywallet.backend.app.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    VerificationService verificationService;
    
    @Autowired
    AuthUtility authUtility;


    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody User user){
        try{
            if(userService.getUserByUserName(user.getUsername())!=null){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(false,"User already exists", null));
            }
            boolean isValidVerification=verificationService.chcekValidity(user.getUsername());
            if(isValidVerification){
                User newUser=userService.createUser(user);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ResponseDTO(true,"User created success", null));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(false,"Verification failed, try later", null));
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody User user){
        try{
            User userDets=userService.loginUser(user);
            if(userDets!=null){
                String token=authUtility.generateToken(user.getUsername());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"Login Success", new UserDTO(user.getUsername(),token)));
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false,"USER NOT FOUND", null));
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestHeader("Authorization") String token){
        try{
            if(authUtility.isValidToken(token)){
                String username=authUtility.getUsernameFromToken(token);
                userService.deleteUser(username);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"User Deleted Success", null));
            }
           return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body(new ResponseDTO(false,"User Unauthorized", null));
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody User user){
        try{
            boolean isValidVerification=verificationService.chcekValidity(user.getUsername());
            if(isValidVerification){
                User modifiedUser=userService.changePassword(user);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"Password Update Success", null));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(false,"Verification failed, try later", null));
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }
    }
}
