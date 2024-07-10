package com.banking.BankApplication.model.request;

import lombok.ToString;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Component
@ToString
@Table(schema="bank", value = "USER_DETAILS")
public class User {

    @Column("FIRST_NAME")
    private String firstName;
    private String lastName;
    private int age;

    private long phoneNumber;
    private String address;

    public User()
    {
    }
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
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
