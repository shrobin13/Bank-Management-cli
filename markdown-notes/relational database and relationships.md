### 🔹 What is a Relational Database?

A **Relational Database (RDB)** is a type of database that stores data in **tables** (rows and columns).

- Each table represents an **entity** (e.g., Customers, Orders, Accounts).
    
- Relationships (links) are defined between tables using **primary keys** and **foreign keys**.
    
- This ensures data is **organized, consistent, and reusable**.
    

👉 Example:

- **Customer table** stores customer info.
    
- **Order table** stores order info.
    
- A relation ensures each order is linked to the right customer.
    

---

### 🔹 Keys in RDB

1. **Primary Key (PK)**
    
    - A unique identifier for each row (e.g., `customer_id`).
        
    - No duplicates, no NULL values.
        
2. **Foreign Key (FK)**
    
    - A column that links one table to another.
        
    - Ensures **referential integrity** (data consistency across tables).
        

---

### 🔹 Types of Relationships in Relational Databases

1. **One-to-One (1:1)**
    
    - A row in Table A relates to **only one row** in Table B.
        
    - Example: Each **Customer** has **one Account Profile**.
        
    
    ```
    Customer (id)  ↔  AccountProfile (customer_id)
    ```
    

---

2. **One-to-Many (1:N)**
    
    - A row in Table A can relate to **many rows** in Table B.
        
    - Example: One **Customer** can make many **Transactions**.
        
    
    ```
    Customer (id)  →  Transaction (customer_id)
    ```
    

---

3. **Many-to-One (N:1)**
    
    - The reverse of one-to-many.
        
    - Example: Many **Transactions** belong to one **Customer**.
        
    
    ```
    Transaction (customer_id)  →  Customer (id)
    ```
    

---

4. **Many-to-Many (M:N)**
    
    - Rows in Table A relate to **multiple rows** in Table B, and vice versa.
        
    - Requires a **junction (link) table**.
        
    - Example:
        
        - Students can enroll in many Courses.
            
        - Courses can have many Students.
            
    
    ```
    Student (id) ↔ Enrollment (student_id, course_id) ↔ Course (id)
    ```
    

---

### 🔹 Why Use Relationships?

✅ **Avoid Data Duplication**

- Store customer info once in the `Customer` table, not repeated in every order.
    

✅ **Ensure Data Integrity**

- Foreign keys prevent invalid data (e.g., an order must reference an existing customer).
    

✅ **Easy Data Management**

- Updates are centralized (change customer name in one table, updates everywhere).
    

✅ **Efficient Queries**

- SQL joins let you combine data from multiple tables (e.g., list all orders of a customer).
    

✅ **Real-World Modeling**

- Relationships mirror real-world scenarios (banking, e-commerce, school systems).
    

---

### 🔹 Real-World Banking Example

- **Customer** (customer_id, name, address)
    
- **Account** (account_id, customer_id, balance) → One customer → Many accounts
    
- **Transaction** (transaction_id, account_id, amount, date) → One account → Many transactions
    
- **Employee** (employee_id, name, branch_id)
    
- **Branch** (branch_id, location) → One branch → Many employees & accounts
    

---

📌 **In short:**  
A **Relational Database** organizes data into related tables.

- Relationships (1:1, 1:N, N:M) allow structured links between entities.
    
- They make databases **cleaner, faster, and more reliable**, while reflecting real-world connections.

---

### What’s the problem if you add everything into the **Customer table**?

Imagine you put **all related data** (like customer details, accounts, transactions, loans, etc.) into just one big **Customer table**.

### Problems:

1. **Data Duplication (Redundancy)**
    
    - If a customer has multiple accounts, you’ll need to repeat the customer’s details (name, address, etc.) for every account.
        
    - Waste of space + higher storage.
        
2. **Update Anomalies**
    
    - If a customer changes their address, you’ll have to update it in multiple rows (for each account/transaction).
        
    - Easy to miss updates → inconsistent data.
        
3. **Insert Anomalies**
    
    - If you want to insert a new customer who hasn’t opened an account yet, you’d have to leave many fields blank (transactions, accounts).
        
4. **Delete Anomalies**
    
    - If you delete a row to remove a customer’s last account, you might also accidentally lose all customer information.
        
5. **Harder Queries**
    
    - Extracting reports becomes more complex because of repeated/unused fields.
        

✅ That’s why we **normalize** data — split it into smaller related tables (`Customer`, `Account`, `Transaction`) and connect them with relationships.

[[✅ Difference Between Relational and Non-Relational Databases]]