package com.banking.application.service;


import com.banking.application.exception.ExceptionManager;
import com.banking.application.model.request.BankAccount;
import com.banking.application.repository.BankAccountRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {
    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private BankAccountService bankAccountService;
    private static BankAccount bankAccount;

    @BeforeAll
    static void setUp() {
        bankAccount = new BankAccount();
        bankAccount.setId("1");
        bankAccount.setAccountNumber("ACC123456");
        bankAccount.setBalance(1000.0f);
        bankAccount.setOwner("John Doe");
    }
    @Test
    void testGetBankAccountById() {
        when(bankAccountRepository.findById("1")).thenReturn(Optional.of(bankAccount));
        BankAccount result = bankAccountService.getBankAccountById("1");
        assertNotNull(result);
        assertEquals("ACC123456", result.getAccountNumber());
    }
    @Test
    void testCreateAccount() {
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);
        BankAccount result = bankAccountService.createAccount(bankAccount);
        assertNotNull(result);
        assertEquals("ACC123456", result.getAccountNumber());
    }
    @Test
    void testGetAccountsByOwnerName() {
        when(bankAccountRepository.findByOwnerName("John", "Doe")).thenReturn(Arrays.asList(bankAccount));
        List<BankAccount> accounts = bankAccountService.getAccountsByOwnerName("John", "Doe");
        assertEquals(1, accounts.size());
        System.out.println(accounts.get(0).getOwner());
    }
    @Test
    void testGetAccountsByBalanceGreaterThan(){
        when(bankAccountRepository.findByBalanceGreaterThan(500)).thenReturn(Arrays.asList(bankAccount));
        List<BankAccount> accounts = bankAccountService.getAccountsByBalanceGreaterThan(500);
        assertEquals(1, accounts.size());
    }
    @Test
    void testGetAccountsByOwnerName_RepositoryThrowsException() {
        // Setup: Define the behavior of the mocked repository to throw an exception
        when(bankAccountRepository.findByOwnerName("John", "Doe"))
                .thenThrow(new RuntimeException("Database connection error"));

        // Action and Assertions: Call the method and expect the RuntimeException to be thrown
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bankAccountService.getAccountsByOwnerName("John", "Doe");
        });

        assertEquals("Database connection error", exception.getMessage());
    }
    @Test
    void testGetAccountsByOwnerName_NullInput() {
        // Action and Assertions: Call the method with null values and expect an IllegalArgumentException
        assertThrows(ExceptionManager.ResourceNotFoundException.class, () -> {
            bankAccountService.getAccountsByOwnerName(null, null);
        });
    }
}
