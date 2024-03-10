package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.enums.TransactionType;
import com.mywallet.backend.app.models.AccountStatement;
import com.mywallet.backend.app.models.Transaction;
import com.mywallet.backend.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AccountStatementRepo extends MongoRepository<AccountStatement, String> {
    List<AccountStatement> findAll();

}