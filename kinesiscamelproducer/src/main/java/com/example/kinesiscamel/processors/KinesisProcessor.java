package com.example.kinesiscamel.processors;

import com.example.kinesiscamel.models.TwitterResponse;
import com.example.kinesiscamel.services.KinesisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KinesisProcessor implements Processor {

  @Autowired private KinesisService kinesisService;

  @Autowired private ObjectMapper objectMapper;

  @Override
  public void process(Exchange exchange) throws Exception {
    log.info("Processing Twitter message");
    String stringResponse = exchange.getIn().getBody(String.class);
    log.info("Deserialized Twitter message to string {}", stringResponse);

    TwitterResponse twitterResponse = objectMapper.readValue(stringResponse, TwitterResponse.class);
    log.info("Deserialized Twitter message to object");

    log.info("Sending twitter response to Kinesis");
    kinesisService.putTwitterResponse(twitterResponse);
  }
}
