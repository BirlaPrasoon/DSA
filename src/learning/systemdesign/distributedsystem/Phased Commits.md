# Phased Commit

**Phased commit** is a technique used in distributed systems and databases to ensure that a transaction or operation is completed successfully across multiple systems or nodes. It is essential for maintaining consistency and coordination in distributed transactions.

## Overview

Phased commit involves a multi-step process to coordinate the completion of a transaction across multiple systems. It ensures that all participating systems agree on whether to commit (finalize) or abort (cancel) the transaction. This prevents inconsistencies where only some systems commit the transaction while others do not.

## Steps in Phased Commit

1. **Prepare Phase**: Each participating system or node receives a request to prepare for the transaction. In this phase:
    - Systems ensure they can commit the transaction if required.
    - Preliminary checks are performed, and resources are reserved.
    - Each system responds with an acknowledgment indicating its readiness to commit.

   **Example:**
   ```java

   // Pseudocode for prepare phase
   prepareTransaction(transactionId);
   if (canCommit()) {
   sendAcknowledgment(YES);
   } else {
   sendAcknowledgment(NO);
   }
   ```

2. **Commit Phase**: Once all systems have indicated readiness to commit:
    - A commit request is sent to each system.
    - Each system finalizes the transaction by making changes permanent.
    - Each system acknowledges the commit request.

   **Example:**
   ```java
   // Pseudocode for commit phase
    sendCommitRequest(transactionId);
    awaitCommitAcknowledgment();
   ```

3. **Recovery Phase**: If any system fails during the commit phase or does not respond:
    - A recovery process is initiated.
    - Systems that did not commit the transaction are rolled back to their previous state.
    - The system attempts to recover from the failure or retry the commit.

   **Example:**
   ```java
   // Pseudocode for recovery phase
   if (commitFails()) {
    rollbackTransaction(transactionId);
   }
   ```

## Importance

- **Consistency**: Ensures that all participating systems agree on the transaction outcome, maintaining data consistency.
- **Coordination**: Coordinates the commit process across multiple systems, avoiding partial commits.
- **Fault Tolerance**: Provides a mechanism for recovery in case of failures during the commit phase.

Phased commit is crucial in distributed systems to handle complex transactions that span multiple systems, ensuring that all parts of the transaction are completed correctly or rolled back if any part fails.


# Commit Protocols

In distributed systems and database management, commit protocols are used to ensure the consistency and coordination of transactions across multiple systems or nodes. Here’s an overview of **1-Phase**, **2-Phase**, and **3-Phase** commit protocols:

## 1-Phase Commit

### Overview

**1-Phase Commit** is a simple commit protocol where a transaction is committed or aborted in a single phase. It is used in environments where all participants are assumed to be reliable and communication is consistent.

### How It Works

1. **Transaction Request**: A coordinator sends a commit request to all participating systems.
2. **Commit or Abort**: Each system either commits or aborts the transaction based on the request.
3. **Acknowledgment**: Each system sends an acknowledgment back to the coordinator.

**Example:**

   ```java
   // Pseudocode for 1-Phase Commit
   sendCommitRequest(transactionId);
   awaitCommitAcknowledgments();
   ```

### Drawbacks

- **Lack of Fault Tolerance**: If a participant fails or does not respond, there is no mechanism for recovery.
- **Limited Coordination**: The protocol does not handle scenarios where participants need to perform preparatory checks before committing.

## 2-Phase Commit (2PC)

### Overview

**2-Phase Commit (2PC)** is a more robust protocol compared to 1-Phase Commit. It ensures that all participants agree on whether to commit or abort a transaction, even in the presence of failures.

### How It Works

1. **Prepare Phase**: The coordinator sends a "prepare" request to all participants, asking them to prepare for committing the transaction. Each participant performs necessary checks and responds with a "yes" (ready to commit) or "no" (not ready).
2. **Commit Phase**: If all participants respond with "yes," the coordinator sends a "commit" request to finalize the transaction. Participants commit the transaction and send acknowledgment. If any participant responds with "no," the coordinator sends an "abort" request, and participants roll back the transaction.

**Example:**

   ```java
   // Pseudocode for 2-Phase Commit
   // Prepare Phase
   sendPrepareRequest(transactionId);
   awaitPrepareResponses();
   
   // Commit Phase
   if (allParticipantsReady()) {
   sendCommitRequest(transactionId);
   } else {
   sendAbortRequest(transactionId);
   }
   awaitCommitAcknowledgments();
   ```

### Advantages

- **Fault Tolerance**: Handles participant failures during the commit process.
- **Coordination**: Ensures all participants agree on the transaction outcome.

### Drawbacks

- **Blocking**: Participants may block waiting for the coordinator if it fails.
- **Complexity**: More complex than 1-Phase Commit.

## 3-Phase Commit (3PC)

### Overview

**3-Phase Commit (3PC)** is an extension of 2PC designed to avoid the blocking problem and improve fault tolerance. It introduces an additional phase to further reduce the risk of blocking.

### How It Works

1. **Can Commit Phase**: The coordinator sends a "can commit" request to all participants, asking them if they are ready to commit. Participants respond with a "yes" (prepared to commit) or "no" (not prepared).
2. **Pre-Commit Phase**: If all participants respond with "yes," the coordinator sends a "pre-commit" request, asking participants to prepare for final commit. Participants acknowledge the pre-commit.
3. **Commit Phase**: The coordinator sends a "commit" request to finalize the transaction. Participants commit the transaction and send acknowledgment.

**Example:**
   ```java
   // Pseudocode for 3-Phase Commit
   // Can Commit Phase
   sendCanCommitRequest(transactionId);
   awaitCanCommitResponses();
   
   // Pre-Commit Phase
   if (allParticipantsReady()) {
   sendPreCommitRequest(transactionId);
   awaitPreCommitAcknowledgments();
   }
   
   // Commit Phase
   sendCommitRequest(transactionId);
   awaitCommitAcknowledgments();
   ```

### Advantages

- **Non-Blocking**: Reduces the risk of participants blocking if the coordinator fails.
- **Enhanced Fault Tolerance**: Handles failures more gracefully than 2PC.

### Drawbacks

- **Complexity**: More complex than 2PC.
- **Overhead**: Additional messaging increases communication overhead.

## Summary

- **1-Phase Commit**: Simple and fast but lacks fault tolerance and coordination.
- **2-Phase Commit (2PC)**: More robust with fault tolerance but can block participants and is complex.
- **3-Phase Commit (3PC)**: Reduces blocking and improves fault tolerance but introduces additional complexity and overhead.

Each commit protocol offers a trade-off between complexity, reliability, and performance. The choice of protocol depends on the requirements of the distributed system and the need for fault tolerance and coordination.
