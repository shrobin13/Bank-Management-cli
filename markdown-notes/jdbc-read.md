Perfect 👍 Here’s a **ready-to-copy note** on the **Read (Select) operation in JDBC**.

---

# 📌 JDBC – Read (Select) Operation

### 🔹 What is Read/Select?

In databases, **Read** refers to fetching data from tables.  
In JDBC, this is done using the `SELECT` SQL statement with `PreparedStatement` and reading results through a `ResultSet`.

---

### 🔹 Steps for Read Operation

1. **Establish Connection**
    
    - Get a connection from `DriverManager` or your connection utility class.
        
2. **Create SQL Select Query**
    
    ```sql
    SELECT * FROM tablename WHERE column = ?;
    ```
    
    - Use `?` placeholders to pass parameters safely.
        
3. **Create PreparedStatement**
    
    ```java
    PreparedStatement ps = conn.prepareStatement(sql);
    ```
    
4. **Set Parameter Values (if needed)**
    
    ```java
    ps.setInt(1, id);
    ```
    
5. **Execute Query**
    
    ```java
    ResultSet rs = ps.executeQuery();
    ```
    
    - Unlike `executeUpdate()`, here we use **`executeQuery()`** because the SQL is `SELECT`.
        
    - It returns a `ResultSet` containing the data.
        
6. **Process ResultSet**
    
    ```java
    while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        ...
    }
    ```
    
    - Use appropriate getter methods (`getInt`, `getString`, `getDate`, etc.) based on column data type.
        
7. **Handle Exceptions & Close Resources**
    
    - Use try-with-resources for auto-closing connection, statement, and result set.
        

---

### 🔹 Example Code

```java
String sql = "SELECT id, name, email, contactno, address FROM customerinformation WHERE email = ?";

try (Connection conn = DatabaseConnection.connectionProvider();
     PreparedStatement ps = conn.prepareStatement(sql)) {

    ps.setString(1, "john@example.com");

    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String contactNo = rs.getString("contactno");
            String address = rs.getString("address");

            System.out.println(id + " | " + name + " | " + email + " | " + contactNo + " | " + address);
        }
    }

} catch (SQLException e) {
    System.out.println("Read failed: " + e.getMessage());
}
```

---

### 🔹 Key Notes

- Use **`executeQuery()`** for `SELECT` statements.
    
- Always iterate over `ResultSet` with `while (rs.next())`.
    
- Access columns either by:
    
    - Column **label/name** → `rs.getString("email")`
        
    - Column **index** → `rs.getString(3)` (less readable).
        
- Use the correct getter method matching column data type.
    
- Remember: **`ResultSet` is forward-only** by default (can’t go backward unless explicitly configured).
    

---

✅ **Summary**:

- The **Read (Select) operation** in JDBC retrieves data using `executeQuery()`.
    
- Results are stored in a `ResultSet`, which you loop through with `rs.next()`.
    
- Use column names or indexes with type-specific getter methods to extract values.
    

---

[[📌 JDBC – Update Operation]]
