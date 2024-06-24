package com.banking.banking.Exception;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist()
    {
        super("Username Already exist");
    }

}
