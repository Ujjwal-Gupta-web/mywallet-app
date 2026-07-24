package com.mywallet.backend.app.models;

import com.mywallet.backend.app.enums.TransactionType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.UUID;

@Embeddable
public class Transaction {
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionWith;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private Double amount;

    protected Transaction() {
    }

    public Transaction(String transactionId,TransactionType transactionType, String transactionWith, Double amount) {
        if(transactionId==null){
            this.transactionId = UUID.randomUUID().toString();
        }
        else{
            this.transactionId =  transactionId;
        }
        this.transactionType = transactionType;
        this.transactionWith = transactionWith;
        this.timestamp = new Date();
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionWith() {
        return transactionWith;
    }

    public void setTransactionWith(String transactionWith) {
        this.transactionWith = transactionWith;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionType=" + transactionType +
                ", transactionWith='" + transactionWith + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
