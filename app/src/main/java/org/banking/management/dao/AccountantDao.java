package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.entity.Accountant;
import org.banking.management.entity.Address;
import org.banking.management.exception.AccountantException;
import org.banking.management.exception.CustomerException;
import org.banking.management.utils.AccountUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AccountantDao {
    public Accountant loginAccountant(String username, String password) throws AccountantException;
    public int addCustomer(String name, String email, String password, int initialDeposit, String country, String city, int postCode, String contactNo) throws CustomerException;
    public String updateCustomerAddress(int customerAccountNumber, Address Address )throws CustomerException;
    public void deleteCustomerAccount(int customerId) throws CustomerException;



    public static void showAllCustomers() throws CustomerException {
        final String query = "SELECT * FROM customer";

        try (Connection connection = DatabaseConnection.connectionProvider();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                throw new CustomerException("No customers found!");
            }

            AccountUtils.printResultSet(resultSet);

        } catch (SQLException sqlEx) {
            throw new CustomerException("Database error occurred: " + sqlEx.getMessage(), sqlEx);
        }
    }

}
