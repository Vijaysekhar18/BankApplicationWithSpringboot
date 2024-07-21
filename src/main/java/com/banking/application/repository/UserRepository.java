package com.banking.application.repository;

import com.banking.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(long phoneNumber);
    User findByFirstNameAndLastName(String firstName ,String lastName);

}



