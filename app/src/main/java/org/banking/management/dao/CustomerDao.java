package org.banking.management.dao;

import org.banking.management.exception.CustomerException;

public interface CustomerDao {
    public int addCustomer(String name, String email, String password, String contactNo, String address, int initialDeposit) throws CustomerException;

    public boolean deposit(int customerId, int amount) throws CustomerException;
}
