package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.service.AccountStatementService;
import com.mywallet.backend.app.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/account")
public class AccountStatementController {

    @Autowired
    AccountStatementService accountStatementService;
    @Autowired
    AuthUtility authUtility;
    @GetMapping("/getAccountStatementByUsername")
    public ResponseEntity<ResponseDTO> getAccountStatementByUsername(@RequestHeader("Authorization") String token){
        try{
            if(authUtility.isValidToken(token)){
                String username=authUtility.getUsernameFromToken(token);
                AccountStatement accountStatement=accountStatementService.getAccountStatementByUsername(username);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"Account fetched success", accountStatement));
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

    @PostMapping("/addTransaction")
    public ResponseEntity<ResponseDTO> addTransaction(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token){
        try{
            if(authUtility.isValidToken(token)) {
                String username = authUtility.getUsernameFromToken(token);
                ResponseDTO responseDTO=accountStatementService.addTransaction(username, transaction);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(responseDTO);
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

    @GetMapping("/getTransactionsByTransactionType/{transactionType}")
    public ResponseEntity<ResponseDTO> getTransactionsByTransactionType(@RequestHeader("Authorization") String token, @PathVariable TransactionType transactionType){
        try{
            if(authUtility.isValidToken(token)) {
                String username = authUtility.getUsernameFromToken(token);
                List<Transaction> transactions=accountStatementService.getTransactionsByTransactionType(username, transactionType);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDTO(true,"Transactions fetched Success", transactions));
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
