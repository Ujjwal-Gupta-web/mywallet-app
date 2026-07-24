package com.mywallet.backend.app.repository;

import com.mywallet.backend.app.models.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AccountStatementRepo extends JpaRepository<AccountStatement, String> {
    List<AccountStatement> findAll();

}
