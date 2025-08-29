
---

## 🔹 What is JDBC?

- **JDBC (Java Database Connectivity)** is an API in Java (`java.sql.*`) that allows Java programs to interact with relational databases.
    
- It defines standard **interfaces** (like `Connection`, `Statement`, `ResultSet`) but does not include database-specific implementations.
    
- Each database (MySQL, Oracle, PostgreSQL, etc.) provides its own **JDBC Driver** (a `.jar` file) that implements these interfaces.
    

📖 Reference: [Oracle JDBC Overview](https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html)

---

## 🔹 Core Classes (in `java.sql`)

- `DriverManager` → Manages JDBC drivers and provides `getConnection()` method.
    
- `Connection` → Represents an active connection to a database.
    
- `Statement` / `PreparedStatement` → Used to execute SQL queries.
    
- `ResultSet` → Represents the result of a query (table-like structure).
    

📖 Reference: [Java SE JDBC API Docs](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)

---

## 🔹 Steps to Connect to a Database (Classic 5-step model)

1. **Load the JDBC Driver** (old way):
    
    ```java
    Class.forName("com.mysql.cj.jdbc.Driver");
    ```
    
    👉 Required in **JDBC 3.0 and earlier**.
    
2. **Create a Connection**
    
    ```java
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/bankingsystem", "root", "");
    ```
    
3. **Create a Statement / PreparedStatement**
    
    ```java
    PreparedStatement ps = conn.prepareStatement(
        "SELECT * FROM accountant WHERE username=?");
    ps.setString(1, "john");
    ```
    
4. **Execute the Query**
    
    ```java
    ResultSet rs = ps.executeQuery();
    ```
    
5. **Process Results**
    
    ```java
    while (rs.next()) {
        System.out.println(rs.getString("username"));
    }
    ```
    
6. **Close Resources** (or use try-with-resources).
    

---

## 🔹 Driver Loading (Old vs New)

- **Old (JDBC ≤ 3):** Needed manual driver loading:
    
    ```java
    Class.forName("com.mysql.cj.jdbc.Driver");
    ```
    
- **New (JDBC 4.0+ / Java 6+):** Automatic driver discovery if `.jar` is on the classpath.
    
    ```java
    Connection conn = DriverManager.getConnection(url, user, pass);
    ```
    
    👉 No need for `Class.forName(...)` anymore (unless using very old setups).
    

📖 Reference: [JDBC 4.0 Auto-Loading](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)

---

## 🔹 Connection URL Format

```
jdbc:mysql://[host]:[port]/[database]
```

Example:

```java
jdbc:mysql://localhost:3306/bankingsystem
```

---

## 🔹 Best Practices

- Use **`PreparedStatement`** instead of `Statement` → prevents SQL Injection.
    
- Always **close connections** (or use `try-with-resources`).
    
- Do not hardcode DB credentials → use config files or environment variables.
    
- Never use the root DB account in production → create a limited-privilege user.
    
- Store passwords **hashed** (e.g., BCrypt) → never plain text.
    

---

✅ Quick Checklist:

- Add JDBC driver `.jar` to classpath (e.g., MySQL Connector/J).
    
- Use `DriverManager.getConnection(...)` to connect.
    
- Prefer `try-with-resources` for automatic cleanup.
    
- Rely on auto-loading (no need for `Class.forName`) if using JDBC 4.0+.
    

---

[[📌 JDBC – Create (Insert) Operation]]
[[📌 JDBC – Read (Select) Operation]]
[[📌 JDBC – Update Operation]]
[[📌 JDBC – Delete Operation]]
[[📌 JDBC CRUD Cheatsheet]]