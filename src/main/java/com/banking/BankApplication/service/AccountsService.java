package com.banking.BankApplication.service;

import com.banking.BankApplication.DAO.AccountsDao;
import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.BankAccountInput;
import com.banking.BankApplication.model.request.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountsService {

    @Autowired
    @Qualifier("accountsDao")
    private AccountsDao accountsDao;

    @Autowired
    @Qualifier("checkingAccountDao")
    private AccountsDao checkingAccountDao;

    @Autowired
    @Qualifier("savingsAccountDao")
    private AccountsDao savingsAccountDao;


    public List<BankAccount> getAccounts() {
        return accountsDao.getAccounts();
    }

//    public BankAccount createAccount(BankAccountInput bankAccountInput) {
//        BankAccount newAccount = new BankAccount(
//                bankAccountInput.getAccountNumber(),
//                bankAccountInput.getBalance(),
//                bankAccountInput.getOwner()
//        );
//        accountsDao.save(newAccount);
//        return newAccount;
//    }
//
//    public BankAccount updateAccountInService(String id, float newBalance, String newOwner) {
//        BankAccount myAccount = accountsDao.getById(id);
//        myAccount.setBalance(newBalance);
//        myAccount.setOwner(newOwner);
//        return myAccount;
//    }
//
//    public void deleteAccount(String id) {
//        accountsDao.deleteById(id);
//    }

    public List<BankAccount> getAccounts(String accountType) {
        if ("checking".equalsIgnoreCase(accountType)) {
            return checkingAccountDao.getAccounts();
        } else if ("savings".equalsIgnoreCase(accountType)) {
            return savingsAccountDao.getAccounts();
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }

    public BankAccount createAccount(BankAccountInput bankAccountInput, String accountType) {
        BankAccount newAccount = new BankAccount(
                bankAccountInput.getAccountNumber(),
                bankAccountInput.getBalance(),
                bankAccountInput.getOwner()
        );
        if(Objects.isNull(accountType)) {
            accountsDao.save(newAccount);
        } else if ("checking".equalsIgnoreCase(accountType)) {
            checkingAccountDao.save(newAccount);
        } else if ("savings".equalsIgnoreCase(accountType)) {
            savingsAccountDao.save(newAccount);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        return newAccount;
    }

    public BankAccount updateAccountInService(String id, float newBalance, String newOwner, String accountType) {
        BankAccount myAccount;
        if ("checking".equalsIgnoreCase(accountType)) {
            myAccount = checkingAccountDao.getById(id);
        } else if ("savings".equalsIgnoreCase(accountType)) {
            myAccount = savingsAccountDao.getById(id);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        myAccount.setBalance(newBalance);
        myAccount.setOwner(newOwner);
        return myAccount;
    }

    public void deleteAccount(String id, String accountType) {
        if ("checking".equalsIgnoreCase(accountType)) {
            checkingAccountDao.deleteById(id);
        } else if ("savings".equalsIgnoreCase(accountType)) {
            savingsAccountDao.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }
    public List<BankAccount> getAccountsOnName(String firstName, String lastName) {
        List<BankAccount> accounts = accountsDao.getAccountsByName(firstName ,lastName);
        return accounts;
    }

    public List<BankAccount> getAccountDetailsByAccountNumber(String accountNumber){
        List<BankAccount> accountDetails = accountsDao.getAccountByAccount(accountNumber);
        return accountDetails;
    }

}
