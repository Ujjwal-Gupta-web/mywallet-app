package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.dto.ContactDTO;
import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.service.ContactService;
import com.mywallet.backend.app.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;
    @GetMapping("/getContactsByUsername")
    public ResponseEntity<ResponseDTO> getAllContactsByUsername(@RequestHeader("Authorization") String token){
        try{
            if(AuthUtility.isValidToken(token)) {
                String username = AuthUtility.getUsernameFromToken(token);
                Contact contacts = contactService.getContactsByUsername(username);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"Contacts fetched success", contacts));
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false,"User Unauthorized", null));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }

    }
    @PostMapping("/addContact")
    public ResponseEntity<ResponseDTO> addContact(@RequestHeader("Authorization") String token, @RequestBody ContactDTO contactDTO) throws Exception {
        try{
            if(AuthUtility.isValidToken(token)) {
                String username = AuthUtility.getUsernameFromToken(token);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(contactService.addContact(username, contactDTO.getContact()));
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false,"User Unauthorized", null));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(false,"Something went wrong", e));
        }
    }
}
