package com.banking.banking.Exception;

public class DontHaveAccount extends RuntimeException{
    public DontHaveAccount()
    {
        super("Dont have Account");
    }
}
