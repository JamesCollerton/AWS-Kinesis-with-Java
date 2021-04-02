package com.example.kinesiscamel.processors;

import com.example.kinesiscamel.models.TwitterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KinesisProcessor implements Processor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        String stringResponse = exchange.getIn().getBody(String.class);
        log.info("Processing Twitter message {}", stringResponse);

        TwitterResponse twitterResponse = objectMapper.readValue(stringResponse, TwitterResponse.class);
        log.info("Deserialized Twitter message from response {}", stringResponse);
    }
}
