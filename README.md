# AWS Kinesis with Camel

A small example application for using AWS Kinesis with Java. It is more fully explained in a Medium article [here](https://jc1175.medium.com/aws-kinesis-with-java-c06c700c94a0).

This demonstration uses Java, [Spring Boot](https://spring.io/projects/spring-boot), [Apache Camel](https://camel.apache.org/), [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) and the [Twitter API](https://developer.twitter.com/en/docs). The overall architecture is as below:

![Twitter Example Application (2)](https://user-images.githubusercontent.com/8677801/113471521-d2384980-9454-11eb-95da-9bcb857dd1a6.png)

The purpose of the program is to poll the Twitter API every twenty seconds (using Apache Camel), then persist this data onto an AWS Kinesis Data Stream (using the Java Kinesis SDK). A Kinesis Data Analytics Application then subscribes to the stream and forwards the message onto another stream. This is finally consumed by another Java application (using Spring Cloud Stream) which displays the tweets.
