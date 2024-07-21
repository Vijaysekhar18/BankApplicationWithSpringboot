package com.banking.application.model;

import com.banking.application.model.request.BankAccount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ToString
@Entity
@Table(schema="bank", name = "USER")
public class User {

    @Id
    @Column(name = "PHONE_NUMBER")
    private long phoneNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private int age;
    private String address;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<BankAccount> bankAccounts;

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public User() {}

    public User(String firstName, String lastName, int age, long phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(String firstName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*@Override
    public String toString(){
        String userObjectString = this.lastName +
                "   " + this.firstName +
                "   "+ this.age + "   " +
                this.phoneNumber + "   " +
                this.address +"   " ;
        return userObjectString;
    }*/

/*    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                '}';
    }*/
}
