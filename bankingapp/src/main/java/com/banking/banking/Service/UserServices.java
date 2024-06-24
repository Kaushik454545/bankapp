package com.banking.banking.Service;

import com.banking.banking.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    String createUser(User user);
    String updateUser(String email,User user);
    List<User> getAllUser();
    String deleteUser(String email);
}
