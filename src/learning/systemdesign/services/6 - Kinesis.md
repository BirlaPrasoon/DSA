# Amazon Kinesis

## What is Amazon Kinesis?

**Amazon Kinesis** is a suite of services provided by AWS designed to handle real-time streaming data. It enables you to collect, process, and analyze large streams of data records in real-time. This is useful for applications that require real-time analytics, such as monitoring, log processing, and data ingestion.

## Components of Amazon Kinesis

Amazon Kinesis is composed of several key services:

1. **Amazon Kinesis Data Streams**:
    - **Description**: A scalable and durable real-time data streaming service that allows you to continuously collect and process large amounts of data records in real-time.
    - **Use Cases**: Real-time analytics, log and event data processing, and data integration.

2. **Amazon Kinesis Data Firehose**:
    - **Description**: A fully managed service that reliably delivers streaming data to data lakes, data stores, and analytics services. It simplifies the process of loading streaming data into Amazon S3, Amazon Redshift, Amazon Elasticsearch Service, and Splunk.
    - **Use Cases**: Data loading and transformation, real-time analytics.

3. **Amazon Kinesis Data Analytics**:
    - **Description**: A service that allows you to process and analyze streaming data using standard SQL queries. It enables real-time analytics on data streams without needing to write complex processing code.
    - **Use Cases**: Real-time analytics, creating real-time dashboards, and generating alerts.

4. **Amazon Kinesis Video Streams**:
    - **Description**: A service for collecting, processing, and analyzing streaming video data. It is used for video ingestion and real-time processing of video streams.
    - **Use Cases**: Video surveillance, machine learning with video data, and video analytics.

## APIs and Operations

### 1. Amazon Kinesis Data Streams

**API Operations**:
- `CreateStream`: Creates a new Kinesis data stream.
- `DescribeStream`: Returns information about a Kinesis data stream.
- `PutRecord`: Adds a single data record to a Kinesis stream.
- `GetRecords`: Retrieves data records from a Kinesis stream.
- `GetShardIterator`: Returns a shard iterator to retrieve records from a shard.
- `ListStreams`: Lists all the Kinesis data streams in your account.
- `DeleteStream`: Deletes a Kinesis data stream and its data.

**Example Code** (Java using AWS SDK):

```java
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.CreateStreamRequest;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.GetRecordsRequest;
import com.amazonaws.services.kinesis.model.GetRecordsResult;
import com.amazonaws.services.kinesis.model.Record;

public class KinesisExample {
public static void main(String[] args) {
AmazonKinesis kinesisClient = AmazonKinesisClientBuilder.defaultClient();

        // Create a new stream
        kinesisClient.createStream(new CreateStreamRequest().withStreamName("my-stream").withShardCount(1));

        // Put a record into the stream
        kinesisClient.putRecord(new PutRecordRequest()
            .withStreamName("my-stream")
            .withData(ByteBuffer.wrap("This is a sample record".getBytes()))
            .withPartitionKey("partitionkey"));

        // Get records from the stream
        GetRecordsRequest getRecordsRequest = new GetRecordsRequest()
            .withShardIterator("shard_iterator"); // Use a valid shard iterator
        GetRecordsResult result = kinesisClient.getRecords(getRecordsRequest);

        for (Record record : result.getRecords()) {
            System.out.println(new String(record.getData().array()));
        }
    }
}
```

### 2. Amazon Kinesis Data Firehose

**API Operations**:
- `CreateDeliveryStream`: Creates a new delivery stream.
- `PutRecord`: Adds a single data record to a delivery stream.
- `PutRecordBatch`: Adds multiple data records to a delivery stream.
- `DescribeDeliveryStream`: Returns information about a delivery stream.
- `ListDeliveryStreams`: Lists all the delivery streams in your account.

**Example Code** (Java using AWS SDK):

```java
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kinesisfirehose.model.CreateDeliveryStreamRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordResult;

public class FirehoseExample {
public static void main(String[] args) {
AmazonKinesisFirehose firehoseClient = AmazonKinesisFirehoseClientBuilder.defaultClient();

        // Create a new delivery stream
        firehoseClient.createDeliveryStream(new CreateDeliveryStreamRequest()
            .withDeliveryStreamName("my-firehose")
            .withS3DestinationConfiguration(new S3DestinationConfiguration()
                .withBucketARN("arn:aws:s3:::my-bucket")
                .withRoleARN("arn:aws:iam::123456789012:role/firehose_role")));

        // Put a record into the delivery stream
        PutRecordResult result = firehoseClient.putRecord(new PutRecordRequest()
            .withDeliveryStreamName("my-firehose")
            .withRecord(new Record().withData(ByteBuffer.wrap("This is a sample record for Firehose".getBytes()))));

        System.out.println("Record ID: " + result.getRecordId());
    }
}
```

### 3. Amazon Kinesis Data Analytics

**API Operations**:
- `CreateApplication`: Creates a new Kinesis Data Analytics application.
- `DescribeApplication`: Returns information about a Kinesis Data Analytics application.
- `UpdateApplication`: Updates an existing Kinesis Data Analytics application.
- `DeleteApplication`: Deletes a Kinesis Data Analytics application.
- `StartApplication`: Starts the processing of a Kinesis Data Analytics application.
- `StopApplication`: Stops the processing of a Kinesis Data Analytics application.

**Example Code** (Java using AWS SDK):

```java
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalytics;
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalyticsClientBuilder;
import com.amazonaws.services.kinesisanalytics.model.CreateApplicationRequest;
import com.amazonaws.services.kinesisanalytics.model.StartApplicationRequest;

public class AnalyticsExample {
public static void main(String[] args) {
AmazonKinesisAnalytics analyticsClient = AmazonKinesisAnalyticsClientBuilder.defaultClient();

        // Create a new application
        analyticsClient.createApplication(new CreateApplicationRequest()
            .withApplicationName("my-application")
            .withInputs(new Input().withNamePrefix("input")
                .withKinesisStreamsInput(new KinesisStreamsInput().withResourceARN("arn:aws:kinesis:region:account-id:stream/stream-name"))
                .withInputSchema(new InputSchema().withRecordColumns(new RecordColumn().withName("columnName").withSqlType("VARCHAR(64)"))
                    .withRecordFormat(new RecordFormat().withRecordFormatType("JSON"))))
            .withApplicationCode("SELECT * FROM input_stream"));

        // Start the application
        analyticsClient.startApplication(new StartApplicationRequest().withApplicationName("my-application"));
    }
}
```

### 4. Amazon Kinesis Video Streams

**API Operations**:
- `CreateStream`: Creates a new Kinesis video stream.
- `DescribeStream`: Returns information about a Kinesis video stream.
- `PutMedia`: Sends video data to a Kinesis video stream.
- `GetMedia`: Retrieves video data from a Kinesis video stream.
- `ListStreams`: Lists all the Kinesis video streams in your account.

**Example Code** (Java using AWS SDK):

```java
import com.amazonaws.services.kinesisvideo.AmazonKinesisVideo;
import com.amazonaws.services.kinesisvideo.AmazonKinesisVideoClientBuilder;
import com.amazonaws.services.kinesisvideo.model.CreateStreamRequest;
import com.amazonaws.services.kinesisvideo.model.PutMediaRequest;

public class VideoExample {
public static void main(String[] args) {
AmazonKinesisVideo videoClient = AmazonKinesisVideoClientBuilder.defaultClient();

        // Create a new video stream
        videoClient.createStream(new CreateStreamRequest()
            .withStreamName("my-video-stream")
            .withDataRetentionInHours(24));

        // Put media into the stream (Typically involves sending video data from a video capture device or file)
    }
}
```

## Conclusion

Amazon Kinesis provides a set of powerful tools for managing and analyzing real-time streaming data. By understanding its components and APIs, you can efficiently collect, process, and analyze data streams to gain actionable insights and drive real-time decision-making in your applications.
