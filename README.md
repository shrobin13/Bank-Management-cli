---

# 🏦 Banking Management System (CLI)

A **Java-based CLI (Command Line Interface) application** for managing a simple banking system.
Built with **JDBC** and **MySQL** for persistent data storage.

---

## ✨ Features

* 👨‍💼 **Accountant login** (username + password verification).
* 👤 **Manage Customers** (add, update, delete, view).
* 💳 **Manage Accounts** (create accounts, deposit, withdraw, balance check).
* 🔐 **Secure Login** using `PreparedStatement` to prevent SQL injection.
* 🗄 **Database Integration** with MySQL (JDBC).
* ⚡ CLI-based, lightweight, and easy to run.

---

## 🛠️ Tech Stack

* **Java SE** (JDK 21)
* **JDBC API (`java.sql`)**
* **MySQL** (Database)
* **Gradle** (for dependency management)

---

## 📂 Project Structure

```
banking-management/
│
├── src/main/java/
│   ├── org/banking/management/
│   │   ├── dao/                # DAO interfaces
│   │   ├── enity(model)/       # POJO classes (Accountant, Customer, etc.)
│   │   ├── exceptions/         # Custom exceptions
│   │   └── databaseconnection/ # DB connection helper
│   │
│   └── App.java                # CLI entry point
└──  build.gradle.kts           # (gradle with kotlin)

├── README.md                   # Project documentation
```

---

## ⚙️ Setup & Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/shrobin13/banking-management.git
   cd banking-management
   ```

2. **Set up MySQL database**

   ```sql
   CREATE DATABASE bankingsystem;

   USE bankingsystem;

   CREATE TABLE accountant (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       email VARCHAR(100),
       password VARCHAR(100) NOT NULL
   );

   CREATE TABLE customer (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100) UNIQUE,
       balance DECIMAL(10,2) DEFAULT 0.0
   );
   ```

3. **Configure Database Connection**
   Update `DatabaseConnection.java` with your credentials:

   ```java
   String url = "jdbc:mysql://localhost:3306/bankingsystem";
   String user = "root";
   String password = "your_password";
   ```

4. **Compile & Run**

   ```bash
   javac -d out src/main/java/org/banking/management/**/*.java
   java -cp out:mysql-connector-j-9.0.0.jar org.banking.management.Main
   ```

---

## 🚀 Usage

After running the app, you’ll see CLI options, e.g.:

```
=== Banking Management CLI ===
1. Accountant Login
2. Add Customer
3. View Customers
4. Deposit Money
5. Withdraw Money
6. Exit
```

👉 Follow prompts to interact with the system.

---

## 🔒 Security Notes

* Passwords should be **hashed (e.g., BCrypt)** before storing in the DB.
* Never hardcode DB credentials — move them to **environment variables** in production.
* Always use `PreparedStatement` (already implemented) to prevent SQL injection.

---

## 🛡️ Error Handling

* Custom exceptions like `AccountantException` are used for meaningful error messages.
* Database errors are wrapped in user-friendly exceptions.

---

## 📚 Learning References

* [Oracle JDBC Basics](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
* [MySQL Connector/J Docs](https://dev.mysql.com/doc/connector-j/en/)
* [Java SQL Package Docs](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)

---

## 👨‍💻 Author

* **Sheikh Robin**
* 🎓 CSE Student | 💻 Java Enthusiast
* 🌐 [GitHub](https://github.com/shrobin13)

---
