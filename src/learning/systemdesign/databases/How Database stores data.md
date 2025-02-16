# DBMS Row Storage

This README provides an overview of how rows are typically stored in Database Management Systems (DBMS).

## Overview

Database Management Systems (DBMS) use various techniques to optimize the storage, retrieval, and management of data. While specific implementations may vary, many relational databases follow similar principles for row storage.

## Storage Structure

1. **Table Space**
    - Logical storage structures containing one or more tables.

2. **Pages or Blocks**
    - Fixed-size units (typically 4KB to 16KB).
    - Smallest unit of data transfer between disk and memory.

3. **Rows**
    - Stored contiguously within pages.
    - Each row contains a header and column data.

## Row Components

1. **Row Header**
    - Contains metadata about the row.
    - Examples: Row ID, transaction information.

2. **Column Data**
    - Actual data for each column in the row.
    - Fixed-length fields stored in-line.
    - Variable-length fields may be stored separately with pointers.

3. **Record Identifiers (RID)**
    - Unique identifier for each row.
    - Often includes information about the row's physical location.

## Additional Storage Concepts

1. **Indexes**
    - Separate structures for quick row lookup.
    - Store key values with pointers to actual row data.

2. **BLOB and CLOB Handling**
    - Large objects often stored separately.
    - Rows contain pointers to large object data.

3. **Compression**
    - Various techniques to reduce storage requirements.
    - Can be applied at row, page, or column level.

4. **Partitioning**
    - Large tables may be divided into separate physical partitions.

5. **Tablespaces and Segments**
    - Used in some DBMS for more granular management.

## Visual Representation

Here's a simplified ASCII representation of how rows might be stored in a page:

```
+------------------+
| Page Header      |
+------------------+
| Row 1 Header     |
| Row 1 Column 1   |
| Row 1 Column 2   |
| ...              |
+------------------+
| Row 2 Header     |
| Row 2 Column 1   |
| Row 2 Column 2   |
| ...              |
+------------------+
| ...              |
+------------------+
| Free Space       |
+------------------+
```

## Important Notes

- This overview represents common practices in traditional relational databases.
- Specific implementations can vary significantly between different DBMS products.
- Modern databases may use alternative storage models like column-oriented storage for certain workloads.
- Database technology is continually evolving, with new storage techniques being developed to address emerging needs.

For detailed information on a specific DBMS, please consult the official documentation of that particular database system.


# DBMS Page Structure

This README provides a detailed visual representation of a typical database page structure and explains its components.

## Page Structure Overview

A database page is typically a fixed-size unit of storage. Here's a visual representation of a page:

```
+----------------------------------+
|           Page Header            |
+----------------------------------+
|         Free Space Pointer       |
+----------------------------------+
|                                  |
|                                  |
|           Row Directory          |
|                                  |
|                                  |
+----------------------------------+
|                                  |
|                                  |
|                                  |
|            Row Data              |
|                                  |
|                                  |
|                                  |
+----------------------------------+
|            Free Space            |
+----------------------------------+
```

## Page Components

### 1. Page Header
- **Size**: Typically 96-120 bytes
- **Contains**:
    - Page type (data, index, etc.)
    - Page number
    - Checksum for data integrity
    - LSN (Log Sequence Number) for recovery
    - Pointers to previous and next pages

### 2. Free Space Pointer
- **Size**: 2-4 bytes
- **Purpose**: Points to the start of free space in the page

### 3. Row Directory
- **Size**: Variable
- **Contains**: Slot entries pointing to the start of each row
- **Entry format**:
```
  +----------------+----------------+
  | Offset (2 bytes)| Length (2 bytes)|
  +----------------+----------------+
```

### 4. Row Data
- **Size**: Variable
- **Contains**: Actual row data
- **Row format**:
- 
```
  +------------+-------------+-------------+-----+-------------+
  | Row Header | Column 1 Data| Column 2 Data| ... | Column N Data|
  +------------+-------------+-------------+-----+-------------+
```
### 5. Free Space
- **Size**: Variable
- **Purpose**: Available space for new rows or row expansions

## Row Header Details

The row header typically contains:

- Row ID or ROWID
- Transaction information
- Number of columns
- Null bitmap (indicating which columns are null)

## Important Notes

- Actual page structures can vary between different DBMS implementations.
- Page sizes are typically powers of 2, ranging from 4KB to 16KB.
- Some systems use slotted pages where row data grows from the bottom up.
- Advanced features like row versioning or MVCC may introduce additional complexities.

For specific details on page structure for a particular DBMS, consult the official documentation or technical resources for that system.

