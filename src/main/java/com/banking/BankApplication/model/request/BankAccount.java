package com.banking.BankApplication.model.request;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
//@Setter
//@Getter
@AllArgsConstructor
@Builder
public class BankAccount {
    private String id;
    private  String accountNumber;
    private float balance;
    private String owner;

    private long ownerPhoneNumber;

    public BankAccount(){}

    public BankAccount(String id, String accountNumber, float balance, String owner) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }
    public BankAccount(String accountNumber, float balance, String owner) {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }


}
