package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.AccountStatementDao;
import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountStatementServiceTest {

    @Mock
    private AccountStatementDao accountStatementDao;

    @Mock
    private EmailService emailService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountStatementService accountStatementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAccountStatementByUsername_Success() {
        String username = "testuser";
        AccountStatement expectedAccountStatement = new AccountStatement(username,new ArrayList<>(),0.0);
        when(accountStatementDao.getAccountStatementByUsername(username)).thenReturn(expectedAccountStatement);

        AccountStatement result = accountStatementService.getAccountStatementByUsername(username);

        assertEquals(expectedAccountStatement, result);
    }
//TEST AGAIN
//    @Test
//    public void testAddTransaction_MoneySent_Success() {
//        String username = "testuser";
//        String transactionWith="testuser1";
//        Transaction transaction = new Transaction("tid", TransactionType.MONEY_SENT, transactionWith, 50.0);
//        List<Transaction> transactions=new ArrayList<>();
//        transactions.add(transaction);
//        AccountStatement accountStatement = new AccountStatement(username, transactions, 100.0);
//        when(accountStatementDao.getAccountStatementByUsername(username)).thenReturn(accountStatement);
//        when(userService.getUserByUserName(transactionWith)).thenReturn(new User(transactionWith,"123456"));
//        when(accountStatementDao.addTransaction(accountStatement)).thenReturn(accountStatement);
//        AccountStatement result = accountStatementService.addTransaction(username, transaction);
//
//        assertEquals(accountStatement, result);
//        assertEquals(50.0, result.getBalance());
////        assertEquals(1, result.getTransactions().size());
//        verify(emailService, times(1)).sendTransactionUpdate(username, transaction);
//    }
//
//    @Test
//    public void testAddTransaction_MoneySent_InsufficientBalance() {
//        String username = "testuser";
//        AccountStatement accountStatement = new AccountStatement(username, new ArrayList<>(), 10.0);
//        Transaction transaction = new Transaction(null, TransactionType.MONEY_SENT, "receiver", 20.0);
//        when(accountStatementDao.getAccountStatementByUsername(username)).thenReturn(accountStatement);
//
//        AccountStatement result = accountStatementService.addTransaction(username, transaction);
//
//        assertEquals(accountStatement, result);
//        assertEquals(10.0, accountStatement.getBalance());
//        assertEquals(0, accountStatement.getTransactions().size());
//        verify(emailService, never()).sendTransactionUpdate(username, transaction);
//    }
//
//    @Test
//    public void testAddTransaction_MoneySent_UserNotFound() {
//        String username = "testuser";
//        AccountStatement accountStatement = new AccountStatement(username, new ArrayList<>(), 100.0);
//        Transaction transaction = new Transaction(null, TransactionType.MONEY_SENT, "nonexistentuser", 50.0);
//        when(accountStatementDao.getAccountStatementByUsername(username)).thenReturn(accountStatement);
//        when(userService.getUserByUserName("nonexistentuser")).thenReturn(null);
//
//        AccountStatement result = accountStatementService.addTransaction(username, transaction);
//
//        assertEquals(accountStatement, result);
//        assertEquals(100.0, accountStatement.getBalance());
//        assertEquals(0, accountStatement.getTransactions().size());
//        verify(emailService, never()).sendTransactionUpdate(username, transaction);
//    }
//
//    @Test
//    public void testHandleCashback_CashbackUnlocked() {
//        AccountStatement accountStatement = new AccountStatement("testuser", new ArrayList<>(), 100.0);
//        accountStatement.setCasbackAvailable(false);
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(null, TransactionType.MONEY_SENT, "receiver", 100.0));
//        when(accountStatementDao.getAccountStatementByUsername("testuser")).thenReturn(accountStatement);
//
//        accountStatementService.handleCashback(accountStatement);
//
//        assertEquals(true, accountStatement.isCasbackAvailable());
//        verify(emailService, times(1)).sendCashBackUnloacked("testuser");
//    }
//
//    @Test
//    public void testHandleCashback_CashbackNotUnlocked() {
//        AccountStatement accountStatement = new AccountStatement("testuser", new ArrayList<>(), 100.0);
//        accountStatement.setCasbackAvailable(true);
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(null, TransactionType.MONEY_SENT, "receiver", 50.0));
//        transactions.add(new Transaction(null, TransactionType.CASHBACK, "ADMIN", 5.0));
//        when(accountStatementDao.getAccountStatementByUsername("testuser")).thenReturn(accountStatement);
//
//        accountStatementService.handleCashback(accountStatement);
//
//        assertEquals(true, accountStatement.isCasbackAvailable());
//        verify(emailService, never()).sendCashBackUnloacked("testuser");
//    }
//
    @Test
    public void testGetTransactionsByTransactionType_Success() {
        String username = "testuser";
        TransactionType transactionType = TransactionType.MONEY_SENT;
        List<Transaction> transactions=new ArrayList<>();
        transactions.add(new Transaction("tid1",TransactionType.MONEY_ADDED,"SELF",50.0));
        transactions.add(new Transaction("tid2",TransactionType.MONEY_WITHDRAWN,"SELF",50.0));
        transactions.add(new Transaction("tid3",TransactionType.MONEY_RECEIVED,"testuser2",50.0));
        transactions.add(new Transaction("tid4",TransactionType.MONEY_SENT,"testuesr3",20.0));
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction("tid4",TransactionType.MONEY_SENT,"testuesr3",20.0));
        when(accountStatementDao.getAccountStatementByUsername(username)).thenReturn(new AccountStatement(username,transactions,30.0));
        when(accountStatementDao.getTransactionsByTransactionType(username, transactionType)).thenReturn(expectedTransactions);

        List<Transaction> result = accountStatementService.getTransactionsByTransactionType(username, transactionType);

        assertEquals(expectedTransactions, result);
    }
}
