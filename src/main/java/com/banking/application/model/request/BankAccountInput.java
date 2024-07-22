package com.banking.application.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class BankAccountInput {

    @NotBlank(message = "Account Number is mandatory")
    @Size(min = 5, max = 20, message = "Account Number must be between 5 and 20 characters")
    private String accountNumber;

    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero")
    private float balance;

    @NotBlank(message = "Owner Name is mandatory")
    @Size(max = 100, message = "Owner name cannot exceed 10 characters")
    private String owner;

    public BankAccountInput() {
    }

    public BankAccountInput(String accountNumber, float balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
