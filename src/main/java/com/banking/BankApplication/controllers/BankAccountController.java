package com.banking.BankApplication.controllers;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> listAccounts() {
        return bankAccountService.getAccounts();
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createAccount(bankAccount);
    }

    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PutMapping("/{id}")
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount bankAccountDetails) {
        return bankAccountService.updateAccount(id, bankAccountDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountService.deleteAccount(id);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<BankAccount> getAccountsDetails(@PathVariable String firstName, @PathVariable String lastName) {
        return bankAccountService.getAccountsByOwnerName(firstName, lastName);
    }

    @GetMapping("/details/{accountNumber}")
    public BankAccount getAccountDetailsByAccountNum(@PathVariable String accountNumber) {
        return bankAccountService.getAccountDetailsByAccountNumber(accountNumber);
    }

    @GetMapping("/balance/{amount}")
    public List<BankAccount> getAccountsByBalanceGreaterThan(@PathVariable float amount) {
        return bankAccountService.getAccountsByBalanceGreaterThan(amount);
    }
}
