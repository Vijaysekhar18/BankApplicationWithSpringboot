package com.banking.BankApplication.service;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.repository.BankAccountRepository;
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
        return bankAccountRepository.findByOwnerName(firstName, lastName);
    }

    public BankAccount getAccountDetailsByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    public List<BankAccount> getAccountsByBalanceGreaterThan(float amount) {
        return bankAccountRepository.findByBalanceGreaterThan(amount);
    }
}
