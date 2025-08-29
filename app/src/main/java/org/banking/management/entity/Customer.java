package org.banking.management.entity;

public class Customer {
    private int accountNumber;
    private int balance;
    private String name;
    private String password;
    private String email;
    private String contactNo;
    private String address;


    public Customer(int accountNumber, int balance, String name, String email, String password, String contactNo, String address) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.address = address;
    }

    public Customer(){}

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString(){
        return "Customer [ accountnumber: " +  this.accountNumber + " name: " + this.name + " balance: "+ this.balance + " password: " + this.password + " mail: "  + this.email + " contact no: " + this.contactNo + " address: " + this.address;
    }

    public int getBalance() {
        return balance;
    }
}
