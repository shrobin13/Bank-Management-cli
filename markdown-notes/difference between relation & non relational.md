
---


| Feature          | Relational Database (SQL)                              | Non-Relational Database (NoSQL)                                         |
| ---------------- | ------------------------------------------------------ | ----------------------------------------------------------------------- |
| **Data Storage** | Tables with rows & columns                             | Collections, documents, key-value pairs, graphs                         |
| **Structure**    | Fixed schema (must define columns)                     | Flexible schema (can store different fields in each document)           |
| **Relations**    | Uses **primary key & foreign key** to link tables      | Usually no joins — relations handled in app or via embedding            |
| **Best For**     | Structured data with relationships (banking, ERP, CRM) | Unstructured/large-scale data (social media, IoT, logs, real-time apps) |
| **Scalability**  | Vertical (add more power to one server)                | Horizontal (add more servers easily)                                    |
| **Examples**     | MySQL, PostgreSQL, Oracle, SQL Server                  | MongoDB, Cassandra, Firebase, DynamoDB                                  |

---

### ✅ Example:

**Relational (Banking System):**

- `Customer` table
    
- `Account` table (linked to customer)
    
- `Transaction` table (linked to account)
    

**Non-Relational (Social Media App):**

- A single `User` document might store:
    
    ```json
    {
      "id": 1,
      "name": "Alice",
      "posts": [
        {"id": 101, "content": "Hello"},
        {"id": 102, "content": "My lunch today"}
      ],
      "friends": [2, 3, 5]
    }
    ```
    
- No separate `Post` or `Friends` table — data is nested inside the user document.
    

---

📌 **In short**:

- Putting too many columns in `Customer` → **duplication, anomalies, messy data**.
    
- **Relational DBs** = great for structured, consistent data with relationships.
    
- **Non-Relational DBs** = great for flexible, high-volume, unstructured or rapidly changing data.
    

---