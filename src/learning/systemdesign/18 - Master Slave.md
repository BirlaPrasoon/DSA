# Master-Slave Architecture

## What is Master-Slave Architecture?

**Master-Slave Architecture** is a design pattern used in distributed systems where one server (the master) acts as the primary source of data and controls the system, while other servers (the slaves) replicate the data from the master. This architecture is often used for data replication, load balancing, and high availability.

## Key Components

1. **Master**: The primary server responsible for handling write operations and propagating changes to the slaves. It often performs the main data processing and serves as the authoritative source of data.
2. **Slaves**: Secondary servers that replicate data from the master. Slaves handle read operations and can be used to distribute the read load, enhancing performance and scalability.

## Use Cases

1. **Database Replication**: Ensuring data redundancy and high availability by replicating data from a master database to one or more slave databases.
2. **Load Balancing**: Distributing read queries across multiple slave servers to reduce the load on the master and improve overall system performance.
3. **High Availability**: Providing fault tolerance by maintaining multiple copies of data across different servers.

## Advantages

- **Scalability**: Distributing read operations across multiple slaves can improve system performance and scalability.
- **Fault Tolerance**: In case the master fails, a slave can be promoted to master, ensuring continuity of service.
- **Load Distribution**: Offloading read operations from the master server to slaves can help balance the load and improve response times.

## Disadvantages

- **Write Bottleneck**: The master server can become a bottleneck if it is overwhelmed with write operations, as all writes must go through it.
- **Data Consistency**: Ensuring data consistency between the master and slaves can be challenging, especially in systems with high write frequencies.

## Example of Master-Slave Database Replication

### MySQL Master-Slave Replication

1. **Configure the Master**:
    - Edit the MySQL configuration file (`my.cnf`):

      ```plaintext
      [mysqld]
      server-id=1
      log-bin=mysql-bin
      ```
    - Restart the MySQL server.
    - Create a replication user:

      ```sql
      CREATE USER ```replica```@```%``` IDENTIFIED BY ```password```;
      GRANT REPLICATION SLAVE ON *.* TO ```replica```@```%```;
      ```

2. **Configure the Slave**:
    - Edit the MySQL configuration file (`my.cnf`):

      ```plaintext
      [mysqld]
      server-id=2
      ```
    - Restart the MySQL server.
    - Set up the slave to replicate from the master:

      ```sql
      CHANGE MASTER TO
      MASTER_HOST=```master_host_ip```,
      MASTER_USER=```replica```,
      MASTER_PASSWORD=```password```,
      MASTER_LOG_FILE=```mysql-bin.000001```,
      MASTER_LOG_POS=154;
      START SLAVE;
      ```

3. **Check Replication Status**:

   ```sql
   SHOW SLAVE STATUS\G
   ```

## Example of Master-Slave Architecture in Code

### Java Example: Simple Master-Slave Implementation

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterSlaveExample {
private static List<String> slaves = new ArrayList<>();
private static String master = "Master Server";

    public static void main(String[] args) {
        // Initialize slave servers
        slaves.add("Slave1");
        slaves.add("Slave2");

        // Simulate write operation to master
        writeToMaster("Important Data");

        // Simulate read operation from slaves
        for (String slave : slaves) {
            readFromSlave(slave);
        }
    }

    public static void writeToMaster(String data) {
        System.out.println("Writing data to " + master + ": " + data);
    }

    public static void readFromSlave(String slave) {
        System.out.println("Reading data from " + slave);
    }
}
```

## Conclusion

The Master-Slave architecture is a powerful design pattern used to enhance performance, scalability, and availability in distributed systems. By understanding its components, use cases, and configurations, you can effectively leverage this architecture to build robust and scalable systems.
