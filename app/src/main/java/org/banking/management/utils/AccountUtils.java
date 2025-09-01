package org.banking.management.utils;

import org.banking.management.databaseconnection.DatabaseConnection;
import org.banking.management.exception.CustomerException;

import java.sql.*;

public class AccountUtils {

    public static void showIndividualAccountInfo(int customerId) throws CustomerException {
        final String QUERY = """
        SELECT
            c.id AS customer_id,
            c.name,
            c.email,
            a.country,
            a.city,
            a.post_code,
            a.contact_no,
            s.balance
        FROM customer c
        LEFT JOIN address a ON c.id = a.user_id
        LEFT JOIN savings s ON c.id = s.customer_id
        WHERE c.id = ?
    """;

        try (Connection conn = DatabaseConnection.connectionProvider();
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    printCustomerInfo(rs);
                } else {
                    throw new CustomerException("No customer found with id: " + customerId);
                }
            }

        } catch (SQLException e) {
            throw new CustomerException("Error fetching customer info", e);
        }
    }

    private static void printCustomerInfo(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(rs.getInt("customer_id")).append("\n")
                .append("Name: ").append(rs.getString("name")).append("\n")
                .append("Email: ").append(rs.getString("email")).append("\n")
                .append("Current Balance: ").append(rs.getInt("balance")).append("\n")
                .append("Contact No: ").append(rs.getString("contact_no")).append("\n")
                .append("Address: ").append(rs.getInt("post_code"))
                .append(", ").append(rs.getString("city"))
                .append(", ").append(rs.getString("country")).append("\n");

        System.out.println(sb);
    }



    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                System.out.print((value != null ? value.toString() : "NULL") + "\t");
            }
            System.out.println();
        }
    }
}
