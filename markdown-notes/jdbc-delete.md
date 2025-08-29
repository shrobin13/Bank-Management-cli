# 📌 JDBC – Delete Operation

### 🔹 What is Delete?

In databases, **Delete** refers to removing existing records from a table.  
In JDBC, this is done using the `DELETE` SQL statement with a `PreparedStatement`.

---

### 🔹 Steps for Delete Operation

1. **Establish Connection**

   - Get a connection using `DriverManager` or a connection utility class.

2. **Create SQL Delete Query**

   ```sql
   DELETE FROM tablename WHERE id = ?;
   ```

   - Always use a `WHERE` clause to avoid deleting all rows! ⚠️

3. **Create PreparedStatement**

   ```java
   PreparedStatement ps = conn.prepareStatement(sql);
   ```

4. **Set Parameter Values**

   ```java
   ps.setInt(1, id);  // record ID to delete
   ```

5. **Execute Update**

   ```java
   int rowsDeleted = ps.executeUpdate();
   ```

   - Returns the number of rows affected.

6. **Check Delete Result**

   ```java
   if (rowsDeleted > 0) {
       System.out.println("Record deleted successfully!");
   } else {
       System.out.println("No record found with the given ID.");
   }
   ```

7. **Handle Exceptions & Close Resources**

   - Use try-with-resources for auto-closing.

---

### 🔹 Example Code

```java
String sql = "DELETE FROM customerinformation WHERE id = ?";

try (Connection conn = DatabaseConnection.connectionProvider();
     PreparedStatement ps = conn.prepareStatement(sql)) {

    ps.setInt(1, 1); // Delete record where id = 1

    int rowsDeleted = ps.executeUpdate();

    if (rowsDeleted > 0) {
        System.out.println("Customer deleted successfully!");
    } else {
        System.out.println("No customer found with the given ID.");
    }

} catch (SQLException e) {
    System.out.println("Delete failed: " + e.getMessage());
}
```

---

### 🔹 Key Notes

- Use **`executeUpdate()`** for `DELETE` statements.
- Return value = **number of rows deleted**.
- If result is `0`, no rows matched the condition.
- **Always use a WHERE clause** → otherwise all rows will be deleted! ⚠️
- Use parameterized queries (`?`) to prevent SQL injection.

---

✅ **Summary**:

- The **Delete operation** in JDBC removes records using `executeUpdate()`.
- A `WHERE` condition must be specified to target specific rows.
- The return value tells how many rows were deleted.

---

Now you have a **complete CRUD note set (Create, Read, Update, Delete)** 🚀
