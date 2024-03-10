package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.AccountStatementDao;
import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountStatementService {

    @Autowired
    AccountStatementDao accountStatementDao;
    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    public AccountStatement getAccountStatementByUsername(String username) {
        return accountStatementDao.getAccountStatementByUsername(username);
    }

    public AccountStatement addTransaction(String username, Transaction transaction) {
        AccountStatement accountStatement=accountStatementDao.getAccountStatementByUsername(username);
        AccountStatement accountStatementRes=accountStatement;
        Double amount=transaction.getAmount();
        //add middleware to ckheck if the username exists or not
        if(accountStatement==null){
            AccountStatement accountStatement1=new AccountStatement(username,new ArrayList<Transaction>(),0.0);
            accountStatementDao.addTransaction(accountStatement1);
            accountStatement=accountStatementDao.getAccountStatementByUsername(username);
        }
        if(accountStatement!=null){
            double balance=accountStatement.getBalance();
            switch(transaction.getTransactionType()){
                case TransactionType.MONEY_SENT:
                    if(transaction.getAmount()<=balance){
                        String username1=transaction.getTransactionWith();
                        if(userService.getUserByUserName(username1)!=null){
                            AccountStatement accountStatement1=accountStatementDao.getAccountStatementByUsername(username1);

                                Transaction transaction1=new Transaction(
                                        transaction.getTransactionId(),
                                        TransactionType.MONEY_RECEIVED,
                                        username,
                                        transaction.getAmount()
                                );

                                List<Transaction> transactionList=accountStatement.getTransactions();
                                transactionList.add(transaction);
                                accountStatement.setTransactions(transactionList);

                                if(accountStatement1==null){
                                    accountStatement1=new AccountStatement(username1,new ArrayList<Transaction>(),0.0);
                                }

                                List<Transaction> transactionList1=accountStatement1.getTransactions();
                                transactionList1.add(transaction1);
                                accountStatement1.setTransactions(transactionList1);
                                double balance1=accountStatement1.getBalance();
                                accountStatement.setBalance(balance-amount);
                                accountStatement1.setBalance(balance1+amount);
                                accountStatementRes=accountStatementDao.addTransaction(accountStatement);
                                accountStatementDao.addTransaction(accountStatement1);

                                emailService.sendTransactionUpdate(username,transaction);
                                emailService.sendTransactionUpdate(username1,transaction1);

                                this.handleCashback(accountStatement);
                        }
                        else{
                            System.out.println("USER NOT FOUND");
                            return accountStatementRes;
                        }
                    }
                    else{
                        System.out.println("INSUFFICIENT BALANCE");
                        return accountStatementRes;
                    }
                    break;
                case TransactionType.MONEY_WITHDRAWN:
                    if(balance>=transaction.getAmount()){
                        accountStatement.setBalance(balance-transaction.getAmount());
                        List<Transaction> transactionList=accountStatement.getTransactions();
                        transactionList.add(transaction);
                        accountStatement.setTransactions(transactionList);
                        accountStatementRes=accountStatementDao.addTransaction(accountStatement);
                        emailService.sendTransactionUpdate(username,transaction);
                    }
                    else{
                        System.out.println("INSUFFICIENT BALANCE");
                        return accountStatementRes;
                    }
                    break;
                case TransactionType.MONEY_ADDED:
                    System.out.println("INSIDE MONEY_ADDED");
                    System.out.println(transaction);
                    List<Transaction> transactionList=accountStatement.getTransactions();
                    transactionList.add(transaction);
                    accountStatement.setTransactions(transactionList);
                    accountStatement.setBalance(balance+transaction.getAmount());
                    System.out.println(accountStatement);
                    accountStatementDao.addTransaction(accountStatement);
                    emailService.sendTransactionUpdate(username,transaction);
                    if(accountStatement.isCasbackAvailable()){
                        double cashback=0.0;
                        if(accountStatement.getTransactions().size()==1){
//                            0.05*amount cahsback upto 1000
                            cashback=0.05*transaction.getAmount();
                            if(cashback>1000.0) cashback=1000.0;
                        }
                        else{
//                            0.01*amount cahsback upto 100
                            cashback=0.05*transaction.getAmount();
                            if(cashback>100.0) cashback=100.0;
                        }
                        accountStatement.setCasbackAvailable(false);
                        Transaction cashbackTransaction=new Transaction(null,TransactionType.CASHBACK,"ADMIN",cashback);
                        transactionList.add(cashbackTransaction);
                        accountStatement.setTransactions(transactionList);
                        accountStatement.setBalance(accountStatement.getBalance()+cashback);
                        accountStatementRes=accountStatementDao.addTransaction(accountStatement);
                        emailService.sendTransactionUpdate(username,cashbackTransaction);
                    }
                    System.out.println("done");
                    break;
            }

        }
        else{
            System.out.println("ERROR");
            return accountStatementRes;
        }
        return accountStatementRes;
    }

    private void handleCashback(AccountStatement accountStatement) {
        if(accountStatement.isCasbackAvailable()) return;
        List<Transaction> transactions=accountStatement.getTransactions().reversed();
        Double moneySentAmount=0.0;
        for(Transaction transaction:transactions){
            if(transaction.getTransactionType()==TransactionType.CASHBACK){
                break;
            }
            else if(transaction.getTransactionType()==TransactionType.MONEY_SENT){
                moneySentAmount+=transaction.getAmount();
            }
        }
        if(moneySentAmount>=150.0){
            accountStatement.setCasbackAvailable(true);
            accountStatementDao.addTransaction(accountStatement);
            emailService.sendCashBackUnloacked(accountStatement.getUsername());
        }
    }


    public List<Transaction> getTransactionsByTransactionType(String username, TransactionType transactionType) {
        return accountStatementDao.getTransactionsByTransactionType(username,transactionType);
    }
}



