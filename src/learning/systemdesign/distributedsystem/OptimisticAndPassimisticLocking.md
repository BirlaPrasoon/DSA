## Pessimistic vs. Optimistic Locking

**Optimistic and pessimistic locking** are two strategies used to manage concurrent access to data in a database. They both aim to prevent data inconsistencies but use different approaches to achieve this.

### Pessimistic Locking

* **Approach:** A pessimistic lock is acquired on a resource before a transaction starts, preventing other transactions from accessing or modifying the resource until the lock is released. This ensures that the transaction has exclusive access to the data.
* **Implementation:** Pessimistic locking is typically implemented using database-level mechanisms like locks or semaphores.
* **Pros:** Guarantees data consistency and avoids conflicts.
* **Cons:** Can lead to lower concurrency if transactions are long-running, as other transactions will be blocked.

### Optimistic Locking

* **Approach:** An optimistic lock assumes that conflicts are rare and doesn't acquire locks until the transaction is about to commit. If a conflict is detected during commit, the transaction is rolled back and retried.
* **Implementation:** Optimistic locking is typically implemented by storing a version number or timestamp with the data. When a transaction updates the data, it compares the current version number with the one it read at the beginning of the transaction. If they don't match, a conflict has occurred.
* **Pros:** Higher concurrency as transactions are not blocked while waiting for locks.
* **Cons:** Requires additional logic to handle conflicts, and there's a risk of lost updates if conflicts are frequent.

### Choosing the Right Approach

The choice between optimistic and pessimistic locking depends on several factors:

* **Expected concurrency:** If you anticipate high levels of concurrency, optimistic locking might be more suitable to avoid blocking transactions.
* **Conflict frequency:** If conflicts are frequent, pessimistic locking might be more appropriate to ensure data consistency.
* **Transaction complexity:** Pessimistic locking might be simpler to implement for complex transactions.
* **Performance requirements:** The performance implications of each approach will depend on your specific use case and database system.

**In summary:**

* **Pessimistic locking** is suitable for scenarios where data consistency is paramount and conflicts are expected to be frequent.
* **Optimistic locking** is suitable for scenarios where high concurrency is desired and conflicts are expected to be rare.

By carefully considering these factors, you can choose the most appropriate locking strategy for your application.


# Optimistic and Pessimistic Locking in MySQL

## Overview

Locking mechanisms are essential for managing concurrent transactions and ensuring data integrity in a database. There are two primary strategies: **Optimistic Locking** and **Pessimistic Locking**. This document explains both approaches and demonstrates how to implement them in MySQL.

---

## 1. Optimistic Locking in MySQL

### Concept

Optimistic Locking assumes that conflicts between transactions are rare. Instead of locking the data upfront, the system checks whether the data has been changed by another transaction when attempting to commit. This is typically implemented by adding a `version` or `timestamp` column to the table.

- The system uses version or timestamp fields to detect changes.
- If a conflict is detected during commit, the transaction is rolled back or retried.

### How to Implement

1. **Add a `version` column** to the table.
2. **Check the version** during the `UPDATE` operation. If the version has changed, the update fails.

### Schema Example
```sql
CREATE TABLE Product ( id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), price DECIMAL(10, 2), version INT );
```

### Example: Update with Version Check
```sql
UPDATE Product SET price = 150.00, version = version + 1 WHERE id = 1 AND version = 1;
```

If another transaction updates the row and increments the `version`, the update will affect **0 rows**, signaling a conflict.

### Transaction Flow Example

1. **Transaction 1** reads the product with `version = 1`:
```sql
SELECT id, name, price, version FROM Product WHERE id = 1;
```

2. **Transaction 2** also reads the product with `version = 1`.

3. **Transaction 1** updates the product:

```sql
UPDATE Product SET price = 200.00, version = version + 1 WHERE id = 1 AND version = 1;
```

4. **Transaction 2** tries to update the product but fails:

```sql
UPDATE Product SET price = 300.00, version = version + 1 WHERE id = 1 AND version = 1;
```


Since the `version` has changed (to 2), **Transaction 2**'s update will not affect any rows.

### Handling Conflict

If a conflict occurs, you can either:
- **Retry** the transaction by reading the updated data.
- **Abort** the operation and notify the user.

---

## 2. Pessimistic Locking in MySQL

### Concept

Pessimistic Locking assumes that conflicts are likely. When a row is read, it is locked so that no other transactions can modify or read it until the lock is released. This ensures that no other transaction can interfere until the current transaction finishes.

- Rows are locked when they are read.
- Other transactions are blocked until the lock is released.

### How to Implement

1. Use `SELECT ... FOR UPDATE` to lock rows for updating.
2. Use `SELECT ... LOCK IN SHARE MODE` to lock rows for reading.

### Example: Pessimistic Locking with `FOR UPDATE`

```sql
START TRANSACTION;

-- Lock the row for update SELECT id, name, price FROM Product WHERE id = 1 FOR UPDATE;

-- Update the product UPDATE Product SET price = 150.00 WHERE id = 1;

COMMIT;
```


### Transaction Flow Example

1. **Transaction 1** locks the row:

```sql
START TRANSACTION; SELECT id, name, price FROM Product WHERE id = 1 FOR UPDATE;
```

2. **Transaction 2** tries to lock the same row but is blocked:
```sql
START TRANSACTION; SELECT id, name, price FROM Product WHERE id = 1 FOR UPDATE;
```

3. **Transaction 1** updates the row and commits:

```sql
UPDATE Product SET price = 200.00 WHERE id = 1; COMMIT;
```

4. **Transaction 2** resumes and can now proceed.

---

## Optimistic vs Pessimistic Locking: Key Differences

| Feature                | **Optimistic Locking**                           | **Pessimistic Locking**                          |
|------------------------|--------------------------------------------------|--------------------------------------------------|
| **When Lock is Acquired** | No lock is acquired upfront, conflict is checked at commit. | Lock is acquired when data is read. Other transactions are blocked. |
| **Conflict Handling**   | Conflict detected at commit based on version/timestamp. | Prevents conflict by blocking other transactions from accessing the locked data. |
| **Concurrency**         | Higher concurrency; no locks are held until commit. | Lower concurrency, as rows are locked early in the transaction. |
| **Use Case**            | Best for read-heavy systems with low contention. | Best for write-heavy systems where conflicts are likely. |
| **Performance Impact**  | Lower overhead unless frequent retries are required. | Higher overhead due to locks, with risk of deadlocks. |

---

## Conclusion

- **Optimistic Locking** is ideal for systems with high concurrency and low contention, such as **read-heavy applications**. It avoids locking rows but checks for conflicts when committing.

- **Pessimistic Locking** is better suited for **write-heavy applications** where the likelihood of conflicts is high. It locks the rows upfront, ensuring that no other transaction can access the data until the current transaction finishes.

Both strategies have trade-offs, and the choice depends on your application’s specific requirements.

## Java Examples 
### OptimisticLockingExample
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptimisticLockingExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/yourdatabase";
        String user = "youruser";
        String password = "yourpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            long productId = 1;
            double newPrice = 150.00;

            // Read product and version
            String selectQuery = "SELECT price, version FROM Product WHERE id = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setLong(1, productId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    double currentPrice = rs.getDouble("price");
                    int version = rs.getInt("version");

                    // Update product price if version matches
                    String updateQuery = "UPDATE Product SET price = ?, version = version + 1 WHERE id = ? AND version = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setDouble(1, newPrice);
                        updateStmt.setLong(2, productId);
                        updateStmt.setInt(3, version);

                        int rowsAffected = updateStmt.executeUpdate();

                        if (rowsAffected == 0) {
                            System.out.println("Update failed due to version conflict.");
                        } else {
                            System.out.println("Update successful.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
### Pessimistic Locking
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PessimisticLockingExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/yourdatabase";
        String user = "youruser";
        String password = "yourpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            long productId = 1;
            double newPrice = 150.00;

            // Start transaction
            conn.setAutoCommit(false);

            // Lock the row for update
            String selectQuery = "SELECT id, price FROM Product WHERE id = ? FOR UPDATE";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setLong(1, productId);
                selectStmt.executeQuery();

                // Update the product price
                String updateQuery = "UPDATE Product SET price = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setDouble(1, newPrice);
                    updateStmt.setLong(2, productId);
                    updateStmt.executeUpdate();
                }

                // Commit transaction
                conn.commit();
                System.out.println("Update successful.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rolled back due to error.");
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
