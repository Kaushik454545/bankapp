package com.banking.banking.Exception;

public class AdminCantHaveAccount extends RuntimeException{
    public AdminCantHaveAccount()
    {
        super("Admin cant open account");
    }
}
