package com.banking.banking.Service.Impl;

import com.banking.banking.Exception.UserAlreadyExist;
import com.banking.banking.Exception.UserNotExist;
import com.banking.banking.Repository.UserRepo;
import com.banking.banking.Service.UserServices;
import com.banking.banking.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserImpl implements UserServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String createUser(User user) {
        User user1=new User();
        if(this.userRepo.findByEmail(user.getEmail()).isEmpty())
        {

                  user1.setFirstName(user.getFirstName());
                  user1.setSecondName(user.getSecondName());
                  user1.setEmail(user.getEmail());
                  user1.setPhoneNumber(user.getPhoneNumber());
                  user1.setPassword(this.passwordEncoder.encode(user.getPassword()));
                  user1.setRole(user.getRole());
                  this.userRepo.save(user1);

        }
        else
        {
            throw new UserAlreadyExist();
        }
        return "Successfully added";
    }

    @Override
    public String updateUser(String email, User user) {
        Optional<User>user1=this.userRepo.findByEmail(email);
        if(user1.isEmpty())
        {
            throw new UserNotExist();
        }
        else
        {
            user1.get().setFirstName(user.getFirstName());
            user1.get().setSecondName(user.getSecondName());
            user1.get().setEmail(email);
            user1.get().setPassword(this.passwordEncoder.encode(user.getPassword()));
            user1.get().setPhoneNumber(user.getPhoneNumber());
            this.userRepo.save(user1.get());
        }
        return "Updated";

    }

    @Override
    public List<User> getAllUser() {
        List<User>users=this.userRepo.findAll();
        return users;
    }

    @Override
    public String deleteUser(String email) {

        int id=this.userRepo.findByEmail(email).get().getId();
        this.userRepo.deleteById(id);
        return "deleted";

    }
}
