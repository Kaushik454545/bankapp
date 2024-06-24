package com.banking.banking.Repository;

import com.banking.banking.entity.Account;
import com.banking.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
   Optional<Account>findByAccountNumber(String accountNumber);
   List<Account>findByUser(User user);
   List<Account>findByBankName(String bankName);
}
