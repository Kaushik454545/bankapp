package com.banking.banking.Service.Impl;

import com.banking.banking.Exception.DontHaveAccount;
import com.banking.banking.Repository.AccountRepo;
import com.banking.banking.Repository.UserRepo;
import com.banking.banking.Service.AccountServices;
import com.banking.banking.entity.Account;
import com.banking.banking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountImpl implements AccountServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    public String generateRandomNumber(int length)
    {
         String character="0123456789";
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++)
        {
            stringBuilder.append(character.charAt(random.nextInt(character.length())));
        }
        return stringBuilder.toString();
    }

    @Override
    public String createAccount(String email, Account account1) {
        Optional<User>optionalUser=this.userRepo.findByEmail(email);
        List<Account> accountList=this.accountRepo.findAll();
        int length=10-account1.getBankName().length();
        String newAccountNumber=this.generateRandomNumber(length);
        String finalAccountNumber=account1.getBankName()+newAccountNumber;
        String finaAccountNumber=new String();
        if(accountList.size()!=0) {
            for (int i = 0; i < accountList.size(); i++) {
                if (finalAccountNumber.equals(accountList.get(i).getAccountNumber())) {
                    String neAccountNumber = this.generateRandomNumber(length);
                     finaAccountNumber = account1.getBankName() + neAccountNumber;
                    finalAccountNumber = finaAccountNumber;
                    i = 0;

                }
            }
        }
        Account account=new Account();
        account.setAccountNumber(finalAccountNumber);
        account.setAmount(0.00);
        account.setBankName(account1.getBankName());
        account.setUser(optionalUser.get());
        this.accountRepo.save(account);
        return "Account created";



    }

    @Override
    public String ammountadded(String accountNumber, double amount) {
          Optional<Account>account=this.accountRepo.findByAccountNumber(accountNumber);
          double finalBalance=account.get().getAmount()+amount;
          account.get().setAmount(finalBalance);
          this.accountRepo.save(account.get());
          return "Amount successfully added";
    }

    @Override
    public String amountWithdraw(String accountNumber, double amount) {
        Optional<Account>account=this.accountRepo.findByAccountNumber(accountNumber);
        if(amount>account.get().getAmount())
        {
            return "Dont have Sufficient balance";
        }
        else {
            double finalBalance=account.get().getAmount()-amount;
            account.get().setAmount(finalBalance);
            this.accountRepo.save(account.get());
            return "Withdraw Successfully";
        }
    }

    @Override
    public List<Map<String, Double>> getBankAccount(String email) {
        List<Map<String, Double>>mapList=new ArrayList<Map<String,Double>>();
        Map<String,Double>hashMap=new HashMap<>();
        Optional<User> userOptional=this.userRepo.findByEmail(email);
          List<Account>accountList=this.accountRepo.findByUser(userOptional.get());
          if(accountList.size()==0)
          {
              throw new DontHaveAccount();
          }
          else
          {
              for(int i=0;i<accountList.size();i++)
              {
                  hashMap.put(accountList.get(i).getAccountNumber(),accountList.get(i).getAmount());
                  mapList.add(hashMap);
              }
              return mapList;
          }

    }

    @Override
    public List<Map<String, String>> getBankAccountfromBankName(String bankName) {
        List<Map<String,String>>mapList=new ArrayList<Map<String,String>>();
        Map<String,String>hashmap=new HashMap<>();
        List<Account>accountList=this.accountRepo.findByBankName(bankName);
        if(accountList.size()==0)
        {
            throw new DontHaveAccount();
        }
        else
        {
            for(int i=0;i<accountList.size();i++)
            {
                int id=accountList.get(i).getUser().getId();
                String firstName=this.userRepo.findById(id).get().getFirstName();
                String lastName=this.userRepo.findById(id).get().getSecondName();
                String name=firstName+" "+lastName;
                hashmap.put(accountList.get(i).getAccountNumber(),name);
                mapList.add(hashmap);
            }
            return mapList;
        }
    }
}
