# Apache Kafka and Kafka Streams

**Apache Kafka** is an open-source distributed event streaming platform used to build real-time data pipelines and streaming applications. Kafka Streams is a stream processing library that helps you process data from Kafka topics in a distributed, fault-tolerant, and scalable way.

## 1. Apache Kafka Overview

Kafka is based on the concept of a **distributed commit log** where messages are stored in **topics** and partitioned for scalability and replicated for fault tolerance.

### 1.1 Core Kafka Components

- **Producers**: Applications that publish (write) data to Kafka topics.
- **Consumers**: Applications that subscribe to topics and process data.
- **Topics**: A category/feed name to which messages are stored and published.
- **Partitions**: Each topic is split into partitions to allow parallelism.
- **Brokers**: Kafka brokers manage the storage of messages in partitions.
- **Zookeeper**: Coordinates Kafka brokers and stores metadata for distributed systems.

## 2. Key Kafka Features

### 2.1 Scalability
Kafka partitions topics across multiple brokers for parallelism, allowing it to handle a high volume of data.

### 2.2 Fault Tolerance
Kafka uses replication for fault tolerance, ensuring that even if a broker fails, data remains available.

### 2.3 High Throughput
Kafka is optimized for high throughput and low latency. It handles large amounts of data in real-time with minimal performance degradation.

## 3. Kafka APIs

### 3.1 Producer API

The producer API allows you to send data to Kafka topics.

#### Example: Writing data to a Kafka topic in Java

```java
import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class KafkaProducerExample {
public static void main(String[] args) {
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), "message-" + i));
        }
        producer.close();
    }
}
```

### 3.2 Consumer API

The consumer API allows you to read data from Kafka topics.

#### Example: Consuming data from a Kafka topic in Java

```java
import org.apache.kafka.clients.consumer.*;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
public static void main(String[] args) {
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "test-group");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("my-topic"));
        
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Offset: %d, Key: %s, Value: %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
```

## 4. Kafka Streams Overview

**Kafka Streams** is a stream processing library that allows you to process data stored in Kafka topics and transform it.

### 4.1 Stream Processing Features

- **Stateless Processing**: Transform, filter, or aggregate streams in real time.
- **Stateful Processing**: Maintain local state with key-value stores.
- **Fault Tolerance**: Automatically recover from failures.

### 4.2 Kafka Streams APIs

Kafka Streams provides a simple API for building streaming applications.

#### Example: Kafka Streams processing in Java

```java
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

public class KafkaStreamsExample {
public static void main(String[] args) {
Properties props = new Properties();
props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream("input-topic");
        stream.filter((key, value) -> value.length() > 5)
              .mapValues(value -> value.toUpperCase())
              .to("output-topic");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
```

## 5. Kafka vs Kafka Streams

### Kafka:
- **Role**: Message broker.
- **Usage**: Used for storing, producing, and consuming messages.
- **Key Use Case**: Log aggregation, real-time analytics.

### Kafka Streams:
- **Role**: Stream processing library built on top of Kafka.
- **Usage**: Used for processing streams of data in real time.
- **Key Use Case**: Stateful and stateless transformations of data streams.

## 6. Use Cases

- **Kafka**: Real-time analytics, log aggregation, event sourcing, message queues.
- **Kafka Streams**: Real-time data transformations, filtering, aggregating data streams.

## 7. Cost Considerations

### Self-hosted Kafka:
- **Infrastructure**: The cost of servers and disk storage.
- **Operational Costs**: Administration, monitoring, scaling, and managing Kafka clusters.

### Managed Kafka (e.g., AWS MSK, Confluent Cloud):
- **Cost of Service**: Varies based on storage, throughput, and message retention.
- **Benefits**: Reduced operational overhead, managed scaling, and monitoring.
