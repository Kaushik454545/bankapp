package com.banking.banking.Service;

import com.banking.banking.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface AccountServices {
     String createAccount(String email, Account account);



     String ammountadded(String accountNumber, double amount);
     String amountWithdraw(String accountNumber,double amount);
     List<Map<String,Double>>getBankAccount(String email);
     List<Map<String,String>>getBankAccountfromBankName(String bankName);


}
