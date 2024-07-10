package com.banking.BankApplication.DAO;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("UserDao")
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public User getByPhoneNumber(long phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber() == phoneNumber) {
                return user;
            }
        }
        return null;
    }

    public void save(User user) {
        users.add(user);

        int insertedRows = jdbcTemplate.update(
                "INSERT INTO bank.USER VALUES (?, ?, ?, ?, ?)",
                user.getFirstName(),user.getLastName(),user.getAge(),
                user.getPhoneNumber(), user.getAddress());
        System.out.print("INSERTED ROWS: " + insertedRows);
    }

    public void delete(long phoneNumber) {
        int deletedRows = jdbcTemplate.update(
                "DELETE FROM bank.USER WHERE phone_Number = ?",
                phoneNumber);
        System.out.print("DELETED ROWS: " + deletedRows);
    }

    public User getUserByPhoneNumber(long phoneNumber) {
        String parameterizedQuery = "SELECT * FROM bank.USER WHERE phone_Number = ?";
        User userFetched = jdbcTemplate.queryForObject(parameterizedQuery, new UserRowMapper(), phoneNumber);
        return userFetched;
    }

    public User getNameByAccountNumber(String accountNumber) {
        String parameterizedQuery = "SELECT a.first_Name, a.last_Name\n" +
                "FROM bank.BANK_ACCCOUNT b \n" +
                "INNER JOIN bank.USER a \n" +
                "ON b.owner_Phone_number = a.phone_Number \n" +
                "WHERE b.account_number = ?";
        User nameOfAccountHolder = jdbcTemplate.queryForObject(parameterizedQuery, new UserRowMapper(), accountNumber);
        return nameOfAccountHolder;

    }

    //public void deleteById(String id) { accounts.removeIf(account -> account.getId().equals(id));
}

