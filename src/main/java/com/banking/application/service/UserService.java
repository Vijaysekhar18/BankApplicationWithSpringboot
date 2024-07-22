package com.banking.application.service;

import com.banking.application.exception.ResourceNotFoundException;
import com.banking.application.model.User;
import com.banking.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * Fetch the User Details using JPA for given FirstName and LastName
     *
     * @param firstName: String representing the first name of the User
     * @param lastName: String representing the first name of the User
     * @return User Object fetched from Database.
     *      - throws ResourceNotFoundException if either JPA throws exception
     *          or User entity not found for given first name and last name
     */
    public User getUserByFirstNameLastName(String firstName , String lastName) {
        User userFromRepository;
        try {
            userFromRepository = userRepository.findByFirstNameAndLastName(firstName , lastName);
        } catch (CannotGetJdbcConnectionException ex) {
            System.err.println("Failed to get JDBC Connection for getUserByFirstNameLastName at " + new Date());
            throw new ResourceNotFoundException("JDBC resource not available", ex);
        }

        if (userFromRepository == null) {
            throw new ResourceNotFoundException("No user found with the given first name: " + firstName + " and last name: " + lastName);
        }
        return userFromRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long phoneNumber) {
        userRepository.deleteById(phoneNumber);
    }


}
