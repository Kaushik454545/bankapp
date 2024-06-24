package com.banking.banking.Controller;

import com.banking.banking.Service.UserServices;
import com.banking.banking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/createUser")
    public ResponseEntity<String>createUser(@RequestBody User user)
    {
        return new ResponseEntity<>(this.userServices.createUser(user), HttpStatus.CREATED);
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<String>updateUser(@PathVariable("email") String email,@RequestBody User user)
    {
        return new ResponseEntity<>(this.userServices.updateUser(email,user),HttpStatus.OK);
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>>getAllUser()
    {
        return new ResponseEntity<>(this.userServices.getAllUser(),HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<String>deleteUSer(@PathVariable("email") String email)
    {


        return new ResponseEntity<>(this.userServices.deleteUser(email),HttpStatus.OK);
    }




}
