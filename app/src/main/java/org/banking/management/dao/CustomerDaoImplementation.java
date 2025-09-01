package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.exception.CustomerException;

import java.sql.*;

public class CustomerDaoImplementation implements CustomerDao{
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
