package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.entity.Address;
import org.banking.management.exception.CustomerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDao {


    public boolean deposit(int customerId, int amount) throws CustomerException;


}
