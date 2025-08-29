package org.banking.management.dao;

import org.banking.management.entity.Accountant;
import org.banking.management.exception.AccountantException;

public interface AccountantDao {
    public Accountant loginAccountant(String username, String password) throws AccountantException;
}
