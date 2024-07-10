package com.banking.BankApplication.service;

import com.banking.BankApplication.DAO.AccountsDao;
import com.banking.BankApplication.DAO.UserDao;
import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.BankAccountInput;
import com.banking.BankApplication.model.request.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User createUser(User user) {
        System.out.println("User Object received is: " + user);
        userDao.save(user);
        return user;
    }
    public void deleteUser(long phoneNumber) {
        userDao.delete(phoneNumber);
    }
    public User getUserByPhoneNumber(long phoneNumber){
        User user = userDao.getUserByPhoneNumber(phoneNumber);
        return user;
    }
    public User getNameByAccountNumber(String accountNumber){
        User userNameByAccNumber = userDao.getNameByAccountNumber(accountNumber);
        return userNameByAccNumber;
    }

}
