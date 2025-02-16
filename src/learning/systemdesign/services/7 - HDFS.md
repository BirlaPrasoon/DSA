# Hadoop Distributed File System (HDFS)

## What is HDFS?

**Hadoop Distributed File System (HDFS)** is the primary storage system of Hadoop. It is designed to store and manage vast amounts of data across many machines in a distributed environment. HDFS is highly scalable, fault-tolerant, and optimized for handling large files with a high-throughput access pattern.

## Key Concepts

1. **Block Storage**: HDFS splits large files into smaller blocks (typically 128MB or 256MB) and stores multiple copies of each block across different nodes to ensure fault tolerance.
2. **Data Replication**: Each block is replicated across multiple nodes (default replication factor is 3) to provide fault tolerance and high availability.
3. **Master-Slave Architecture**: HDFS has a master-slave architecture with the NameNode as the master managing metadata and the DataNodes as slaves storing the actual data blocks.

## Core Components

1. **NameNode**:
    - **Description**: Manages metadata and the file system namespace. It maintains the directory tree of all files and tracks the files and blocks in the cluster.
    - **API Operations**: The NameNode does not have a direct API but is interacted with through HDFS client commands and APIs.

2. **DataNode**:
    - **Description**: Stores the actual data blocks. DataNodes are responsible for serving read and write requests from clients.
    - **API Operations**: The DataNode does not have a direct API but communicates with the NameNode and clients using internal protocols.

## APIs and Operations

### 1. HDFS File System API

**Java API Operations**:
- `FileSystem.get`: Retrieves an instance of the HDFS file system.
- `create`: Creates a new file or overwrites an existing file.
- `open`: Opens an existing file for reading.
- `delete`: Deletes a file or directory.
- `listFiles`: Lists files in a directory.
- `mkdirs`: Creates a directory and any necessary parent directories.

**Example Code** (Java using Hadoop HDFS API):

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import java.io.IOException;

public class HDFSExample {
public static void main(String[] args) throws IOException {
Configuration conf = new Configuration();
conf.set("fs.defaultFS", "hdfs://localhost:9000");

        FileSystem fs = FileSystem.get(conf);

        // Create a new file
        Path newFilePath = new Path("/user/hadoop/newfile.txt");
        FSDataOutputStream outputStream = fs.create(newFilePath);
        outputStream.writeUTF("This is a sample HDFS file.");
        outputStream.close();

        // Read the file
        FSDataInputStream inputStream = fs.open(newFilePath);
        String content = inputStream.readUTF();
        inputStream.close();
        System.out.println("File Content: " + content);

        // List files in a directory
        FileStatus[] statuses = fs.listStatus(new Path("/user/hadoop"));
        for (FileStatus status : statuses) {
            System.out.println("File: " + status.getPath());
        }

        // Delete the file
        fs.delete(newFilePath, false);
    }
}
```

## Conclusion

Hadoop Distributed File System (HDFS) provides a scalable, fault-tolerant storage solution for large-scale data processing. By understanding its core components and APIs, you can effectively manage and interact with distributed data in a Hadoop environment. The provided Java code examples demonstrate basic operations for interacting with HDFS, including file creation, reading, listing, and deletion.
