package com.banking.banking.Controller;

import com.banking.banking.Repository.AccountRepo;
import com.banking.banking.Service.AccountServices;
import com.banking.banking.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    private AccountServices accountServices;
    @PostMapping("/createAccount/{email}")
    public ResponseEntity<String>createAccount(@PathVariable("email") String email, @RequestBody Account account)
    {
        return new ResponseEntity<>(this.accountServices.createAccount(email,account), HttpStatus.CREATED);
    }
    @PutMapping("/customer/amountadded/{accountNumber}/{amount}")
    public ResponseEntity<String>amountAdded(@PathVariable("accountNumber") String accountNumber,@PathVariable("amount")double amount)
    {
        return new ResponseEntity<>(this.accountServices.ammountadded(accountNumber,amount),HttpStatus.OK);
    }
    @PutMapping("/customer/amountwithdraw/{accountNumber}/{amount}")
    public ResponseEntity<String>amountWithdraw(@PathVariable("accountNumber") String accountNumber,@PathVariable("amount")double amount)
    {
        return new ResponseEntity<>(this.accountServices.amountWithdraw(accountNumber,amount),HttpStatus.OK);
    }
    @GetMapping("/getAccount/{email}")
    public ResponseEntity<List<Map<String,Double>>>getAccount(@PathVariable("email") String email)
    {
        return new ResponseEntity<>(this.accountServices.getBankAccount(email),HttpStatus.OK);
    }
    @GetMapping("/getBankAccountfromBank/{bankName}")
    public ResponseEntity<List<Map<String,String>>>getAllAccount(@PathVariable("bankName")String bankName)
    {
        return new ResponseEntity<>(this.accountServices.getBankAccountfromBankName(bankName),HttpStatus.OK);
    }



}
