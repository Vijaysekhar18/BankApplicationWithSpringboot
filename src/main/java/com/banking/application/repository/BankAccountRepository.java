package com.banking.application.repository;

import com.banking.application.model.request.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    // Find all accounts by owner's first and last name
    @Query("SELECT ba FROM BankAccount ba WHERE ba.owner LIKE CONCAT('%', :firstName, '%') AND ba.owner LIKE CONCAT('%', :lastName, '%')")
    List<BankAccount> findByOwnerName(String firstName, String lastName);

    // Find all accounts by account number
    BankAccount findByAccountNumber(String accountNumber);

    // Custom query to find accounts with balance greater than a certain amount
    @Query("SELECT ba FROM BankAccount ba WHERE ba.balance > :amount")
    List<BankAccount> findByBalanceGreaterThan(float amount);
}
