package com.banking.BankApplication.model.request;

import org.springframework.stereotype.Component;

@Component
public class BankAccountInput {
    private  String accountNumber;
    private float balance;
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
