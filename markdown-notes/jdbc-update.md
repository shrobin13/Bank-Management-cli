### 🔹 What is Update?

In databases, **Update** refers to modifying existing records in a table.  
In JDBC, this is done using the `UPDATE` SQL statement with a `PreparedStatement`.

---

### 🔹 Steps for Update Operation

1. **Establish Connection**
    
    - Get a connection using `DriverManager` or your connection utility class.
        
2. **Create SQL Update Query**
    
    ```sql
    UPDATE tablename SET col1 = ?, col2 = ? WHERE id = ?;
    ```
    
    - Use placeholders `?` for parameterized values.
        
3. **Create PreparedStatement**
    
    ```java
    PreparedStatement ps = conn.prepareStatement(sql);
    ```
    
4. **Set Parameter Values**
    
    ```java
    ps.setString(1, newName);
    ps.setString(2, newEmail);
    ps.setInt(3, id);  // condition value
    ```
    
5. **Execute Update**
    
    ```java
    int rowsUpdated = ps.executeUpdate();
    ```
    
    - Returns the number of rows affected.
        
6. **Check Update Result**
    
    ```java
    if (rowsUpdated > 0) {
        System.out.println("Record updated successfully!");
    } else {
        System.out.println("No record found with the given ID.");
    }
    ```
    
7. **Handle Exceptions & Close Resources**
    
    - Use try-with-resources to auto-close.
        

---

### 🔹 Example Code

```java
String sql = "UPDATE customerinformation SET name = ?, email = ?, contactno = ? WHERE id = ?";

try (Connection conn = DatabaseConnection.connectionProvider();
     PreparedStatement ps = conn.prepareStatement(sql)) {

    ps.setString(1, "John Updated");
    ps.setString(2, "john_updated@example.com");
    ps.setString(3, "9876543210");
    ps.setInt(4, 1); // Update record where id = 1

    int rowsUpdated = ps.executeUpdate();

    if (rowsUpdated > 0) {
        System.out.println("Customer updated successfully!");
    } else {
        System.out.println("No customer found with the given ID.");
    }

} catch (SQLException e) {
    System.out.println("Update failed: " + e.getMessage());
}
```

---

### 🔹 Key Notes

- Use **`executeUpdate()`** for `UPDATE` statements.
    
- Returns the **number of rows affected**.
    
- If result is `0`, no rows matched the condition (e.g., wrong ID).
    
- Always include a **WHERE clause** → otherwise all rows may get updated! ⚠️
    
- Parameterized queries (`?`) prevent SQL injection.
    

---

✅ **Summary**:

- The **Update operation** in JDBC modifies existing records using `executeUpdate()`.
    
- You must pass new values for columns and specify a condition in the `WHERE` clause.
    
- The return value indicates how many rows were updated.
    

---

[[📌 JDBC – Delete Operation]]