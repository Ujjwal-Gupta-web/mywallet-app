package com.mywallet.backend.app.controller;

import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.service.AccountStatementService;
import com.mywallet.backend.app.utility.AuthUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountStatementControllerTest {

    @Mock
    private AccountStatementService accountStatementService;

    @Mock
    private AuthUtility authUtility;

    @InjectMocks
    private AccountStatementController accountStatementController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAccountStatementByUsername_Success() {
        String username = "testuser";
        String token = authUtility.generateToken(username);
        AccountStatement accountStatement = new AccountStatement(username,new ArrayList<>(),0.0);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);
        when(accountStatementService.getAccountStatementByUsername(username)).thenReturn(accountStatement);

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getAccountStatementByUsername(token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Account fetched success", responseEntity.getBody().getMessage());
        assertEquals(accountStatement, responseEntity.getBody().getData());
    }

    @Test
    public void testGetAccountStatementByUsername_Unauthorized() {
        String token = "invalid_token";
        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getAccountStatementByUsername(token);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetAccountStatementByUsername_InternalServerError() {
        String username="testuser";
        String token = authUtility.generateToken(username);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getAccountStatementByUsername(token);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }

    @Test
    public void testAddTransaction_Success() {
        String username = "testuser";
        List<Transaction> transactions=new ArrayList<>();
        Transaction transaction = new Transaction("tid",TransactionType.MONEY_ADDED,"SELF",50.0);
        transactions.add(transaction);
        String token = authUtility.generateToken(username);
        AccountStatement accountStatement = new AccountStatement(username,transactions,50.0);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);
        when(accountStatementService.addTransaction(username, transaction)).thenReturn(new ResponseDTO(true,"done",accountStatement));

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.addTransaction(transaction, token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("done", responseEntity.getBody().getMessage());
        assertEquals(accountStatement, responseEntity.getBody().getData());
    }

    @Test
    public void testAddTransaction_Unauthorized() {
        String token = "invalid_token";
        Transaction transaction = new Transaction("tid",TransactionType.MONEY_ADDED,"SELF",50.0);
        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.addTransaction(transaction, token);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testAddTransaction_InternalServerError() {
        String username = "testuser";
        List<Transaction> transactions=new ArrayList<>();
        Transaction transaction = new Transaction("tid",TransactionType.MONEY_ADDED,"SELF",50.0);
        transactions.add(transaction);
        String token = authUtility.generateToken(username);
        AccountStatement accountStatement = new AccountStatement(username,transactions,50.0);
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn("testuser");
        when(accountStatementService.addTransaction(username, transaction)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.addTransaction(transaction, token);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetTransactionsByTransactionType_Success() {
        String username = "testuser";
        String token = authUtility.generateToken(username);
        TransactionType transactionType = TransactionType.MONEY_ADDED;
        List<Transaction> transactions = new ArrayList<>();
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn(username);
        when(accountStatementService.getTransactionsByTransactionType(username, transactionType)).thenReturn(transactions);

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getTransactionsByTransactionType(token, transactionType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Transactions fetched Success", responseEntity.getBody().getMessage());
        assertEquals(transactions, responseEntity.getBody().getData());
    }

    @Test
    public void testGetTransactionsByTransactionType_Unauthorized() {
        String token = "invalid_token";
        TransactionType transactionType = TransactionType.MONEY_ADDED;
        when(authUtility.isValidToken(token)).thenReturn(false);

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getTransactionsByTransactionType(token, transactionType);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User Unauthorized", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetTransactionsByTransactionType_InternalServerError() {
        String username = "testuser";
        String token = authUtility.generateToken(username);
        TransactionType transactionType = TransactionType.MONEY_ADDED;
        when(authUtility.isValidToken(token)).thenReturn(true);
        when(authUtility.getUsernameFromToken(token)).thenReturn("testuser");
        when(accountStatementService.getTransactionsByTransactionType("testuser", transactionType)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<ResponseDTO> responseEntity = accountStatementController.getTransactionsByTransactionType(token, transactionType);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Something went wrong", responseEntity.getBody().getMessage());
    }
}
