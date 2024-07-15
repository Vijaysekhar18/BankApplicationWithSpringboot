package com.banking.BankApplication.service;

import com.banking.BankApplication.model.User;
import com.banking.BankApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByPhoneNumber(long phoneNumber) {
        User userFromRepository = userRepository.findByPhoneNumber(phoneNumber);
        if(Objects.nonNull(userFromRepository)) {
//            System.out.println("Accounts for user: " + userFromRepository + " is " + userFromRepository.getBankAccounts().size());
        }
        return userFromRepository;
    }
    public User getUserByFirstNameLastName(String firstName , String lastName) {
        User userFromRepository = userRepository.findByFirstNameAndLastName(firstName , lastName);
        return userFromRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long phoneNumber) {
        userRepository.deleteById(phoneNumber);
    }


}
