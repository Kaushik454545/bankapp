package com.banking.banking.Exception;

public class UserNotExist extends RuntimeException{
    public UserNotExist()
    {
        super("User Not Exist");
    }
}
