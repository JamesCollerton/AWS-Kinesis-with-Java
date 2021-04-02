package com.example.kinesiscamel.services;

import com.example.kinesiscamel.models.TwitterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequestEntry;
import software.amazon.awssdk.services.kinesis.model.PutRecordsResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KinesisService {

  @Autowired private KinesisClient kinesisClient;

  @Autowired private ObjectMapper objectMapper;

  @Value("${kinesis-stream-name}")
  private String kinesisStreamName;

  public void putTwitterResponse(TwitterResponse twitterResponse) {

    log.info("Creating list of request entries");

    List<PutRecordsRequestEntry> putRecordsRequestEntryList =
        twitterResponse.getData().stream()
            .map(
                twitterResponseItem -> {
                  byte[] bytes;
                  try {
                    bytes = objectMapper.writeValueAsBytes(twitterResponseItem);
                  } catch (IOException e) {
                    throw new RuntimeException("Couldn't write JSON as bytes");
                  }

                  return PutRecordsRequestEntry.builder()
                      .partitionKey(UUID.randomUUID().toString())
                      .data(SdkBytes.fromByteArray(bytes))
                      .build();
                })
            .collect(Collectors.toList());

    log.info("Creating put records request");

    PutRecordsRequest putRecordsRequest =
        PutRecordsRequest.builder()
            .streamName(kinesisStreamName)
            .records(putRecordsRequestEntryList)
            .build();

    log.info("Sending put records request");

    PutRecordsResponse putRecordsResponse = kinesisClient.putRecords(putRecordsRequest);
    log.info("Put records response {}", putRecordsResponse);
  }
}
