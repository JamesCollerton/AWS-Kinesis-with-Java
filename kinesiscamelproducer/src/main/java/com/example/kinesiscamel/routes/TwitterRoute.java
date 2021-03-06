package com.example.kinesiscamel.routes;

import com.example.kinesiscamel.processors.KinesisProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TwitterRoute extends RouteBuilder {

  @Value("${twitter-api.bearer-token}")
  private String bearerToken;

  @Autowired private KinesisProcessor kinesisProcessor;

  @Override
  public void configure() {

    log.info("Configuring Camel route");

    from("timer://test?period=20000")
        .description("Polling tweets from API")
        .log("About to start our initial polling")
        .setHeader(Exchange.HTTP_METHOD)
        .constant(HttpMethod.GET)
        .setHeader("Authorization")
        .simple("Bearer " + bearerToken)
        .to("https://api.twitter.com/2/tweets/search/recent?query=to:BBC")
        .log("This is the status code from the response: ${header.CamelHttpResponseCode}")
        .process(kinesisProcessor);
  }
}
