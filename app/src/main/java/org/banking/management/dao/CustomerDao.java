package org.banking.management.dao;

import org.banking.management.entity.Customer;
import org.banking.management.exception.CustomerException;

public interface CustomerDao {

    public Customer customerLogin(String email, String password)throws CustomerException;
    public boolean deposit(int customerId, int amount) throws CustomerException;
    public int showBalance(int customerId) throws CustomerException;
    public boolean withdrawBalance(int customerId, int withdrawAmount) throws CustomerException;
    public void transferBalance(int customerId, int recipientId, int transferAmount) throws CustomerException;
}
