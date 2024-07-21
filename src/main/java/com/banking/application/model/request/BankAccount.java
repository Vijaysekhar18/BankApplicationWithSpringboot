package com.banking.application.model.request;

import com.banking.application.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @NotBlank(message = "Account Number is mandatory")
    @Size(min = 6, max = 10, message = "Account Number must be between 6 and 10 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "Account Number must be a combination of letters and numbers")
    private String accountNumber;

    @Column(name = "balance")
    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero")
    @DecimalMax(value = "10000.0", message = "Balance cannot be more than 10,000")
    private float balance;

    @Column(name = "owner")
    @NotBlank(message = "Owner is mandatory")
    @Size(min = 3, max = 14, message = "Owner name must be between 3 and 14 characters")
    private String owner;

    @ManyToOne
    @JoinColumn(name="owner_phone_number")
    @JsonIgnoreProperties("bankAccounts")
    private User user;

}
