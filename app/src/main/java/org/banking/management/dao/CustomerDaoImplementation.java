package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.entity.Customer;
import org.banking.management.exception.CustomerException;
import org.banking.management.utils.PasswordUtils;

import java.sql.*;

public class CustomerDaoImplementation implements CustomerDao{
    @Override
    public Customer customerLogin(String email, String password) throws CustomerException {
       final String QUERY = """
               SELECT
               id,
               name,
               email,
               password
               FROM customer
               WHERE email = ?
               """;

       try(Connection conn = DatabaseConnection.connectionProvider();
           PreparedStatement stmt = conn.prepareStatement(QUERY)
       ){
           stmt.setString(1, email);
           try(ResultSet rs = stmt.executeQuery()){
               if(rs.next()){
                   int dbCustomerId = rs.getInt("id");
                   String dbCustomerName = rs.getString("name");
                   String dbCustomerEmail = rs.getString("email");
                   String dbCustomerPass = rs.getString("password");

                   if(PasswordUtils.checkPassword(password, dbCustomerPass)){
                       return new Customer(dbCustomerId, dbCustomerName, dbCustomerEmail, dbCustomerPass);
                   }else{
                       System.out.println("Invalid email or password!");
                   }
               }else{
                   System.out.println("Email doesn't Exist");
               }
           }

       } catch (SQLException e) {
           throw new CustomerException("Something went wrong! " + e.getMessage());
       }

        return null;
    }


    @Override
    public boolean deposit(int customerId, int amount) throws CustomerException{

        if(amount <= 0) throw new CustomerException("Amount is less than 0r equal to 0. Please Enter higher amount!");

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

    @Override
    public int showBalance(int customerId) throws CustomerException {
        final String QUERY = "SELECT balance from savings where customer_id = ?";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement stmt = conn.prepareStatement(QUERY)
        ){
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getInt("balance");
        } catch (SQLException se) {
            throw new CustomerException("Something went wrong!" + se.getMessage());
        }

        return 0;
    }

    @Override
    public boolean withdrawBalance(int customerId, int withdrawAmount) throws CustomerException {
        if(withdrawAmount <= 0 ) throw new CustomerException("Amount is too less, Please enter higher amount!");
        final String QUERY = "UPDATE savings SET balance = balance - ? WHERE customer_id = ?";
        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement stmt = conn.prepareStatement(QUERY)
        ){
            stmt.setInt(1, withdrawAmount);
            stmt.setInt(2, customerId);
            int affectedRow = stmt.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            throw new CustomerException("Something went wrong! " + e.getMessage());
        }
    }

    @Override
    public void transferBalance(int senderId, int recipientId, int transferAmount) throws CustomerException {
        if(transferAmount <= 0) throw new CustomerException("Transfer amount must be greater than zero!");

        if(senderId == recipientId) throw new CustomerException("Sender and Recipient cannot be the same!!");

        final String BALANCE_CHECK = "SELECT balance FROM savings WHERE customer_id = ?";
        final String DEDUCT_QUERY = "UPDATE savings SET balance = balance - ? WHERE customer_id = ?";
        final String CREDIT_QUERY = "UPDATE savings SET balance = balance + ? WHERE customer_id = ?";

        try(Connection conn = DatabaseConnection.connectionProvider();
        ){
            conn.setAutoCommit(false);
            try{

                int senderBalance;
                try(PreparedStatement stmt = conn.prepareStatement(BALANCE_CHECK)){
                    stmt.setInt(1, senderId);
                    try(ResultSet rs = stmt.executeQuery()){
                        if(!rs.next()) throw new CustomerException("Sender Not found with id " + senderId);

                        senderBalance = rs.getInt("balance");
                    }
                }

                if(senderBalance < transferAmount) throw new CustomerException("Insufficient balance to transfer!!");

                try(PreparedStatement stmt = conn.prepareStatement(DEDUCT_QUERY)){
                    stmt.setInt(1, transferAmount);
                    stmt.setInt(2, senderId);
                    if (stmt.executeUpdate() == 0) {
                        throw new CustomerException("Failed to deduct balance from sender");
                    }
                }

                try(PreparedStatement stmt = conn.prepareStatement(CREDIT_QUERY)){
                    stmt.setInt(1, transferAmount);
                    stmt.setInt(2, recipientId);
                    if(stmt.executeUpdate() == 0) throw new CustomerException("Recipient not found with id " + recipientId);
                }

                conn.commit();
                System.out.println(transferAmount + " taka successfully transferred to id: " + recipientId);

            } catch (Exception e) {
                conn.rollback();
                throw new CustomerException("Something went wrong!!");
            }

        } catch (SQLException se) {
            throw new CustomerException("Something went wrong! " + se.getMessage());
        }
    }


}
