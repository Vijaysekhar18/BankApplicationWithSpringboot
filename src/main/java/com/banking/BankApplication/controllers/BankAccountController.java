package com.banking.BankApplication.controllers;

import com.banking.BankApplication.model.request.BankAccount;
import com.banking.BankApplication.model.request.BankAccountInput;
import com.banking.BankApplication.model.request.User;
import com.banking.BankApplication.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BankAccountController {

    @Autowired
    private AccountsService accountsService;

    @GetMapping(value = "/accounts")
    public List<BankAccount> listAccounts(){
        System.out.println("List of accounts");
        return accountsService.getAccounts();
    }

//    @PostMapping(value = "/accounts")
//    public BankAccount createBankAccount(@RequestBody BankAccountInput bankAccountInput){
//        return accountsService.createAccount(bankAccountInput);
//    }

    @PostMapping(value = "/accounts")
    public BankAccount createBankAccount(@RequestBody BankAccountInput bankAccountInput, @RequestParam String accountType) {
        return accountsService.createAccount(bankAccountInput, accountType);
    }

    @GetMapping(value = "accounts/{id}")
    public BankAccount getAccount(@PathVariable String id){
        for (BankAccount account : accountsService.getAccounts()) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

//    @PutMapping(value = "accounts/{id}")
//    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccountInput bankAccountInput){
//        BankAccount bankAccount = accountsService.updateAccountInService(id ,bankAccountInput.getBalance(),bankAccountInput.getOwner());
//        return bankAccount;
//    }
    @PutMapping(value = "accounts/{id}")
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccountInput bankAccountInput, @RequestParam String accountType) {
        return accountsService.updateAccountInService(id, bankAccountInput.getBalance(), bankAccountInput.getOwner(), accountType);
    }

//    @DeleteMapping(value = "accounts/{id}")
//    public void deleteAccount(@PathVariable String id){
//        accountsService.deleteAccount(id);
//    }
    @DeleteMapping(value = "accounts/{id}")
    public void deleteAccount(@PathVariable String id, @RequestParam String accountType) {
        accountsService.deleteAccount(id, accountType);
    }
    @GetMapping(value ="/accounts/{firstName}/{lastName}")
    public List<BankAccount> getAccountsDetails(@PathVariable String firstName,
                                         @PathVariable String lastName){
        List<BankAccount> bankAccounts = accountsService.getAccountsOnName(firstName,lastName);
        return bankAccounts;
    }

    @GetMapping(value ="/accounts/Details/{accountNumber}")
    public List<BankAccount> getAccountDetailsByAccountNum(@PathVariable String accountNumber){
        List<BankAccount> accountDetails = accountsService.getAccountDetailsByAccountNumber(accountNumber);
        return accountDetails;
    }


    // create a API that can find the owner First Name and Last Name based on the Account Number -- JDBC Template
    // create a API that returns all the accounts corresponding to the owner of the given Account Number -- NamedJDBCTemplate
}
