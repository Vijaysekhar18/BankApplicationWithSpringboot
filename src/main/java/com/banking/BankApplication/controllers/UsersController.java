package com.banking.BankApplication.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.BankAccountInput;
import com.banking.BankApplication.model.request.User;
import com.banking.BankApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping(value ="/user/{phoneNumber}")
    public User getUserDetails(@PathVariable long phoneNumber){
        User userDetails = userService.getUserByPhoneNumber(phoneNumber);
        return userDetails;
    }


    @PostMapping(value = "/login")
    public boolean checkUser(@RequestParam(value = "name") String userName, @RequestParam String password){
        if(userName.equals("vijay")&& password.equals("vijay"))
        {
            System.out.println("Login done");
            return true;
        }
        return false;
    }

    @PutMapping(value = "/{username}/login")
    public boolean checkPostUser(@PathVariable String username, @RequestParam String password){
        if(username.equals("vijay")&& password.equals("vijay"))
        {
            System.out.println("Login done");
            return true;
        }
        return false;
    }
    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user){
//            System.out.println("Full name");
//            String fullName = user.getFirstName() + user.getLastName();
            return userService.createUser(user);

    }
    @GetMapping(value = "/user/accounts")
    public String getUserAccount(@RequestParam String firstName){
        System.out.println("Full name");
        return firstName;
    }
    @DeleteMapping(value = "/user/{phoneNumber}")
    public void deleteUser(@PathVariable long phoneNumber) {
        userService.deleteUser(phoneNumber);
        System.out.println("User with phone number " + phoneNumber + " deleted.");
    }
    @GetMapping(value = "/userDetail/{accountNumber}")
    public User getNameByAccountNum(@PathVariable String accountNumber){
        return userService.getNameByAccountNumber(accountNumber);
    }

}
