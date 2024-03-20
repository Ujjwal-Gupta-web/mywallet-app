package com.mywallet.backend.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "accountStatement")
public class AccountStatement {
    @Id
    @Indexed(unique = true)
    @Field("username")
    private String username;
    List<Transaction> transactions;
    Double balance;

    private boolean isCasbackAvailable = true;

    public AccountStatement(String username, List<Transaction> transactions, Double balance) {
        this.username = username;
        this.transactions = transactions;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "username='" + username + '\'' +
                ", transactions=" + transactions +
                ", balance=" + balance +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isCasbackAvailable() {
        return isCasbackAvailable;
    }

    public void setCasbackAvailable(boolean casbackAvailable) {
        isCasbackAvailable = casbackAvailable;
    }
}
