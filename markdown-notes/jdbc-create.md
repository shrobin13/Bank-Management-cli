
### 🔹 What is Create/Insert?

In databases, **Create** refers to inserting new records into a table.  
In JDBC, this is done using `INSERT INTO` SQL statements with a `PreparedStatement`.

---

### 🔹 Steps for Insert Operation

1. **Establish Connection**
    
    - Use `DriverManager` or a connection provider class.
        
2. **Create SQL Insert Query**
    
    ```sql
    INSERT INTO tablename (col1, col2, col3, ...) VALUES (?, ?, ?, ...);
    ```
    
    - Use `?` placeholders for parameters (prevents SQL injection).
        
3. **Create PreparedStatement**
    
    ```java
    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ```
    
    - `Statement.RETURN_GENERATED_KEYS` allows retrieval of auto-generated IDs (like AUTO_INCREMENT primary keys).
        
4. **Set Parameter Values**
    
    ```java
    ps.setString(1, name);
    ps.setString(2, email);
    ps.setString(3, password);
    ...
    ```
    
5. **Execute Update**
    
    ```java
    int rows = ps.executeUpdate();
    ```
    
    - Returns number of rows affected (should be > 0 if successful).
        
6. **Retrieve Generated Keys (Optional)**
    
    ```java
    ResultSet rs = ps.getGeneratedKeys();
    if (rs.next()) {
        int id = rs.getInt(1);
        System.out.println("Inserted record ID: " + id);
    }
    ```
    
7. **Handle Exceptions & Close Resources**
    
    - Always use **try-with-resources** to auto-close connections.
        

---

### 🔹 Example Code

```java
String sql = "INSERT INTO customerinformation (name, email, password, contactno, address) VALUES (?, ?, ?, ?, ?)";

try (Connection conn = DatabaseConnection.connectionProvider();
     PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

    ps.setString(1, "John Doe");
    ps.setString(2, "john@example.com");
    ps.setString(3, "secret");
    ps.setString(4, "1234567890");
    ps.setString(5, "New York");

    int rowsAffected = ps.executeUpdate();

    if (rowsAffected > 0) {
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                System.out.println("Customer added successfully with ID: " + generatedId);
            }
        }
    }

} catch (SQLException e) {
    System.out.println("Insert failed: " + e.getMessage());
}
```

---

### 🔹 Key Notes

- `executeUpdate()` is used for `INSERT`, `UPDATE`, `DELETE` statements.
    
- `executeQuery()` is only for `SELECT`.
    
- Always use `PreparedStatement` instead of `Statement` → prevents SQL injection.
    
- If table has an `AUTO_INCREMENT` primary key → use `RETURN_GENERATED_KEYS` to fetch it.
    
- If no auto-generated column → you can ignore `getGeneratedKeys()` and just rely on `rowsAffected`.
    

---

✅ **Summary**:

- The **Create (Insert) operation** in JDBC is done using `INSERT INTO` with `PreparedStatement`.
    
- Use placeholders (`?`) for safe parameter insertion.
    
- Use `Statement.RETURN_GENERATED_KEYS` if you want the new record’s ID.
    
- `executeUpdate()` → number of rows inserted.
    

---
[[📌 JDBC – Read (Select) Operation]]