package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.entity.Accountant;
import org.banking.management.entity.Address;
import org.banking.management.exception.AccountantException;
import org.banking.management.exception.CustomerException;
import org.banking.management.utils.PasswordUtils;

import java.sql.*;

public class AccountantDaoImplementation implements AccountantDao{
    @Override
    public Accountant loginAccountant(String username, String password) throws AccountantException {
        Accountant accountant = null;
        String sql = "SELECT username, email, password FROM accountant WHERE username = ?";
        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)
        ){
            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){

                    String dbUserName = resultSet.getString("username");
                    String dbEmail = resultSet.getString("email");
                    String dbPassword = resultSet.getString("password");

                    if(PasswordUtils.checkPassword(password, dbPassword)){
                        accountant = new Accountant(dbUserName, dbEmail, dbPassword);
                    }else{
                        System.out.println("Invalid Username or password ");
                    }
                }else{
                    System.out.println("Invalid Username or password ");
                }
            }
        } catch (SQLException e){
            throw new AccountantException("Database Error! " + e.getMessage());
        }

        return accountant;
    }

    @Override
    public int addCustomer(String name, String email, String password, int initialDeposit, String country, String city, int postCode, String contactNo) throws CustomerException {

        int id = -1;

        String customerSql = "INSERT INTO customer (name, email, password) VALUES (?, ?, ?)";
        String savingsSql = "INSERT INTO savings (customer_id, balance) values(?,?)";
        String addressSql = "INSERT INTO address (country, city, post_code, contact_no, user_id) values(?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement customerStatement = conn.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS)
        ){
            customerStatement.setString(1, name);
            customerStatement.setString(2, email);
            customerStatement.setString(3, password);

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

                        try(PreparedStatement addressStatement = conn.prepareStatement(addressSql)){
                            addressStatement.setString(1,country);
                            addressStatement.setString(2,city);
                            addressStatement.setInt(3,postCode);
                            addressStatement.setString(4,contactNo);
                            addressStatement.setInt(5,id);
                            addressStatement.executeUpdate();
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
    public String updateCustomerAddress(int customerAccountNumber, Address address) throws CustomerException {
        String message = "I don't know, something went wrong!";
        String addressSql = "UPDATE address SET country = ?, city = ?, post_code = ?, contact_no = ? WHERE user_id = ?";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement updatedAddressStatement = conn.prepareStatement(addressSql)){
            updatedAddressStatement.setString(1, address.getCountry());
            updatedAddressStatement.setString(2, address.getCity());
            updatedAddressStatement.setInt(3, address.getPost_code());
            updatedAddressStatement.setString(4, address.getContact_no());
            updatedAddressStatement.setInt(5, customerAccountNumber);

            int rowsAffected = updatedAddressStatement.executeUpdate();

            if(rowsAffected == 0){
                throw new CustomerException("No user Found with id: " + customerAccountNumber);
            }

            message = "Customer address updated successfully with the id of " + customerAccountNumber;

        } catch (SQLException se) {
            throw new CustomerException("Something went wrong! " + se.getMessage());
        }

        return message;
    }

    @Override
    public void deleteCustomerAccount(int customerId) throws CustomerException {
        String deleteCustomerSql = "DELETE FROM customer WHERE id = ?";

        try(Connection conn = DatabaseConnection.connectionProvider();
            PreparedStatement deleteCustomerStmt = conn.prepareStatement(deleteCustomerSql)
        ){
            deleteCustomerStmt.setInt(1, customerId);
            int affectedRows = deleteCustomerStmt.executeUpdate();
            if(affectedRows == 0) throw new CustomerException("Customer no found with id: " + customerId);
            System.out.println("Customer Deleted Successfully");
        }catch (SQLException se){
            throw new CustomerException("Something went wrong! " + se.getMessage());
        }
    }


}



