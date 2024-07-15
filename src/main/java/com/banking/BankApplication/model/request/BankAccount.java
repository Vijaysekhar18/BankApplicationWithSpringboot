package com.banking.BankApplication.model.request;

import com.banking.BankApplication.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "bank", name = "BANK_ACCCOUNT")
public class BankAccount {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private float balance;

    @Column(name = "owner")
    private String owner;

    @ManyToOne
    @JoinColumn(name="owner_phone_number")
    @JsonIgnoreProperties("bankAccounts")
    private User user;
}
