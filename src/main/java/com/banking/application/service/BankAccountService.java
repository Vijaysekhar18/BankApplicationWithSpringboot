package com.banking.application.service;



import com.banking.application.model.request.BankAccount;
import com.banking.application.exception.ExceptionManager;
import com.banking.application.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount getBankAccountById(String id){
        Optional<BankAccount> bankAccountFromRepository = bankAccountRepository.findById(id);
        return bankAccountFromRepository.orElse(null);
    }

    public List<BankAccount> getAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount createAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount updateAccount(String id, BankAccount bankAccountDetails) {
        return bankAccountRepository.findById(id)
                .map(bankAccount -> {
                    bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
                    bankAccount.setBalance(bankAccountDetails.getBalance());
                    bankAccount.setOwner(bankAccountDetails.getOwner());
//                    bankAccount.setOwnerPhoneNumber(bankAccountDetails.getOwnerPhoneNumber());
                    return bankAccountRepository.save(bankAccount);
                })
                .orElseGet(() -> {
                    bankAccountDetails.setId(id);
                    return bankAccountRepository.save(bankAccountDetails);
                });
    }

    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }

    public List<BankAccount> getAccountsByOwnerName(String firstName, String lastName) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByOwnerName(firstName, lastName);
        if (bankAccounts == null || bankAccounts.isEmpty()) {
            throw new ExceptionManager.ResourceNotFoundException("No accounts found for the owner with the given first name: " + firstName + " and last name: " + lastName);
        }
        return bankAccounts;
    }

    public BankAccount getAccountDetailsByAccountNumber(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        if (bankAccount == null) {
            throw new ExceptionManager.InvalidInputException("No accounts found for the account Number: " + accountNumber );
        }
        return bankAccount;
    }

    public List<BankAccount> getAccountsByBalanceGreaterThan(float amount) {
        return bankAccountRepository.findByBalanceGreaterThan(amount);
    }
}
