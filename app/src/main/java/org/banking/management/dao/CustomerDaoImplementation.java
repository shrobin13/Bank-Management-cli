package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.exception.CustomerException;

import java.sql.*;

public class CustomerDaoImplementation implements CustomerDao{
    @Override
    public int addCustomer(String name, String email, String password, String contactNo, String address, int initialDeposit) throws CustomerException {

        int id = -1;

        String customerSql = "INSERT INTO customer (name, email, password, contactno, address) VALUES (?, ?, ?, ?, ?)";
        String savingsSql = "INSERT INTO savings (customer_id, balance) values(?,?)";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement customerStatement = conn.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS)
        ){
            customerStatement.setString(1, name);
            customerStatement.setString(2, email);
            customerStatement.setString(3, password);
            customerStatement.setString(4, contactNo);
            customerStatement.setString(5, address);

            int rowsAffected = customerStatement.executeUpdate();

            if(rowsAffected > 0){
                try(ResultSet resultSet = customerStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        id = resultSet.getInt(1);

                         try(PreparedStatement savingsStatement = conn.prepareStatement(savingsSql)){
                             savingsStatement.setInt(1,id);
                             savingsStatement.setInt(2, initialDeposit);
                             savingsStatement.executeUpdate();
                         }

                    }
                }
                System.out.println("Customer added successfully with ID: " + id + " with the initial deposit of: " + initialDeposit);
            }
        } catch (SQLException e) {
            throw new CustomerException("Something went wrong! " + e.getMessage());
        }
        return id;
    }

    @Override
    public boolean deposit(int customerId, int amount) throws CustomerException{
        String depositSql = "UPDATE savings SET balance = balance + ? WHERE customer_id = ?";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement depositStatement = conn.prepareStatement(depositSql)
        ){
            depositStatement.setInt(1, amount);
            depositStatement.setInt(2, customerId);

            int rowsAffected = depositStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new CustomerException("Something went wrong" + e.getMessage() );
        }
    }
}
