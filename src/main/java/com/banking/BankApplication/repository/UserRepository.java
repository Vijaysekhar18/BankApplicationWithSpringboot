package com.banking.BankApplication.repository;

import com.banking.BankApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(long phoneNumber);
    User findByFirstNameAndLastName(String firstName ,String lastName);

}



