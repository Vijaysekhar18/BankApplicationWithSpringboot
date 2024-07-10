package com.banking.BankApplication.DAO;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository("accountsDao")
public class AccountsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<BankAccount> accounts = new ArrayList<>();

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public BankAccount getById(String id) {
        for (BankAccount account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public void save(BankAccount bankAccount) {
        accounts.add(bankAccount);

        int insertedRows = jdbcTemplate.update(
                "INSERT INTO bank.BANK_ACCCOUNT VALUES (?, ?, ?, ?)",
                bankAccount.getId(),bankAccount.getAccountNumber(),
                bankAccount.getBalance(), bankAccount.getOwner());
        System.out.print("INSERTED ROWS: " + insertedRows);
    }
    public List<BankAccount> getAccountsByName(String firstName, String lastName) {
        String parameterizedQuery = "SELECT b.* \n" +
                "FROM bank.BANK_ACCCOUNT b \n" +
                "INNER JOIN bank.USER a \n" +
                "ON b.owner_Phone_number = a.phone_Number \n" +
                "WHERE a.first_Name = ? \n" +
                "AND a.last_Name = ?;";

        List<BankAccount> accountsFetched = jdbcTemplate.query(parameterizedQuery, new AccountRowMapper(), firstName, lastName);
        return accountsFetched;
    }

    public List<BankAccount> getAccountByAccount(String accountNumber) {
        String parameterizedQuery = "SELECT b.*\n" +
                "FROM bank.BANK_ACCCOUNT b\n" +
                "JOIN bank.USER u ON b.owner_Phone_number = u.phone_Number\n" +
                "WHERE b.owner_Phone_number = (\n" +
                "    SELECT b1.owner_Phone_number\n" +
                "    FROM bank.BANK_ACCCOUNT b1\n" +
                "    WHERE b1.account_number = ?)";
        List<BankAccount> accountDetails = jdbcTemplate.query(parameterizedQuery, new AccountRowMapper(), accountNumber);
        return accountDetails;
    }


    public void deleteById(String id) {
        accounts.removeIf(account -> account.getId().equals(id));
    }
}
