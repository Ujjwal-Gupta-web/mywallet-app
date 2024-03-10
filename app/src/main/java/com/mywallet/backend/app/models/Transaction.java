package com.mywallet.backend.app.models;

import com.mywallet.backend.app.enums.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    @Id
    @Field("transactionId")
    private String transactionId;
    private TransactionType transactionType;
    private String transactionWith;
    private Date timestamp;
    private Double amount;

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
