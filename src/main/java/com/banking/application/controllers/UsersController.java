package com.banking.application.controllers;

import com.banking.application.model.User;
import com.banking.application.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{phoneNumber}")
    public User getUserDetails(@PathVariable long phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    /**
     * Added Exception Handliong as well
     * @param firstName
     * @param lastName
     * @return
     */
    @GetMapping(value = "/user/{firstName}/{lastName}")
    public User getUserDetailsByNames(@PathVariable ("name") @NotBlank @Size(max = 10) String firstName, @PathVariable @NotBlank @Size(max = 10) String lastName) {
        return userService.getUserByFirstNameLastName(firstName, lastName);
    }

    @PostMapping(value = "/login")
    public boolean checkUser(@RequestParam(value = "name") String userName, @RequestParam String password) {
        if (userName.equals("vijay") && password.equals("vijay")) {
            System.out.println("Login done");
            return true;
        }
        return false;
    }

    @PutMapping(value = "/{username}/login")
    public boolean checkPostUser(@PathVariable String username, @RequestParam String password) {
        if (username.equals("vijay") && password.equals("vijay")) {
            System.out.println("Login done");
            return true;
        }
        return false;
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/user/accounts")
    public String getUserAccount(@RequestParam String firstName) {
        System.out.println("Full name");
        return firstName;
    }

    @DeleteMapping(value = "/user/{phoneNumber}")
    public void deleteUser(@PathVariable long phoneNumber) {
        userService.deleteUser(phoneNumber);
        System.out.println("User with phone number " + phoneNumber + " deleted.");
    }

}
