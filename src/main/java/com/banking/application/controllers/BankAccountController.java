package com.banking.application.controllers;

import com.banking.application.exception.ExceptionManager;
import com.banking.application.model.request.BankAccount;
import com.banking.application.service.BankAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> listAccounts() {
        return bankAccountService.getAccounts();
    }

    /**
     * https://www.baeldung.com/spring-boot-bean-validation
     * TODO: Refer this and implement the validations for BankAccount Object
     * @param bankAccount
     * @return
//     */
//    @PostMapping
//    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
//        return bankAccountService.createAccount(bankAccount);
//    }
    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody @Valid BankAccount bankAccount) {
        BankAccount createdAccount = bankAccountService.createAccount(bankAccount);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }

    /**
     * https://www.baeldung.com/spring-boot-bean-validation
     * TODO: Refer this and implement the validations for BankAccount Object
     * @param bankAccountDetails
     * @return
     */
//    @PutMapping("/{id}")
//    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount bankAccountDetails) {
//        return bankAccountService.updateAccount(id, bankAccountDetails);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateAccount( @PathVariable @NotNull String id,  @RequestBody @Valid BankAccount bankAccountDetails) {
        BankAccount updatedAccount = bankAccountService.updateAccount(id, bankAccountDetails);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountService.deleteAccount(id);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<BankAccount> getAccountsDetails(
            @PathVariable("firstName") @NotBlank @Size(max = 10) String firstName,
            @PathVariable("lastName") @NotBlank @Size(max = 10) String lastName) {

        if (firstName.length() > 10 || lastName.length() > 10) {
            throw new ExceptionManager.InvalidNameLengthException("First name and last name must be less than 10 characters.");
        }

        List<BankAccount> accounts = bankAccountService.getAccountsByOwnerName(firstName, lastName);
        if (accounts == null || accounts.isEmpty()) {
            throw new ExceptionManager.AccountNotFoundException("No accounts found for " + firstName + " " + lastName);
        }

        return accounts;
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
