package com.banking.BankApplication.DAO;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<BankAccount> {

    @Override
    public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//        BankAccount bank = new BankAccount();
//        bank.setId(rs.getString("Id"));
//        bank.setAccountNumber(rs.getString("account_number"));
//        bank.setBalance(rs.getFloat("balance"));
//        bank.setOwner(rs.getString("owner"));
//        bank.setOwnerPhoneNumber(rs.getLong("owner_Phone_number"));

        BankAccount bankObjectFromBuilder  = BankAccount.builder()
                .id(rs.getString("Id"))
                .accountNumber(rs.getString("account_number"))
                .balance(rs.getFloat("balance"))
                .owner(rs.getString("owner")).build();
        return bankObjectFromBuilder;
    }
}
