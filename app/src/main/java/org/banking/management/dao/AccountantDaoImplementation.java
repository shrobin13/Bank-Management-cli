package org.banking.management.dao;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.entity.Accountant;
import org.banking.management.exception.AccountantException;
import org.banking.management.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        throw new AccountantException("Invalid Username or Password");
                    }
                }else{
                    throw new AccountantException("Invalid Username or Password");
                }
            }
        } catch (SQLException e){
            throw new AccountantException("Database Error! " + e.getMessage());
        }

        return accountant;
    }

}



