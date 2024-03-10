package com.mywallet.backend.app.dao;

import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.repository.AccountStatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountStatementDao {
    @Autowired
    AccountStatementRepo accountStatementRepo;
    public AccountStatement getAccountStatementByUsername(String username){
        return accountStatementRepo.findById(username).orElse(null);
    }


    public AccountStatement addTransaction(AccountStatement accountStatement) {
        return accountStatementRepo.save(accountStatement);
    }

    public List<Transaction> getTransactionsByTransactionType(String username, TransactionType transactionType) {
        AccountStatement accountStatement=this.getAccountStatementByUsername(username);
        return accountStatement.getTransactions().stream()
                .filter(transaction -> transaction.getTransactionType()==transactionType)
                .toList();
    }
}
