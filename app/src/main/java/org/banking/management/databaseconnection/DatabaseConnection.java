package org.banking.management.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
  public static Connection connectionProvider() {
    Connection conn = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    String url = "jdbc:mysql://localhost:3306/bankingsystem";

    try {
      conn = DriverManager.getConnection(url, "root", "password");
    } catch (Exception e) {
      System.out.println("Exception occurred in database");
    }

    return conn;
  }
}
