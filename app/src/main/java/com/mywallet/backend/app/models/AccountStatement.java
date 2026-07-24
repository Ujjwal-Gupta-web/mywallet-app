package com.mywallet.backend.app.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "account_statements")
public class AccountStatement {
    @Id
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transactions", joinColumns = @JoinColumn(name = "username"))
    @OrderColumn(name = "position")
    List<Transaction> transactions;
    Double balance;

    private boolean isCasbackAvailable = true;

    protected AccountStatement() {
    }

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
