package com.example.kinesiscamel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TwitterRoute extends RouteBuilder {

  @Value("${twitter-api.bearer-token}")
  private String bearerToken;

  @Override
  public void configure() {

    from("timer://test?period=20000")
        .description("Streaming tweets from API")
        .log("About to start our initial stream")
        .setHeader(Exchange.HTTP_METHOD)
        .constant(HttpMethod.GET)
        .setHeader("Authorization")
        .simple("Bearer " + bearerToken)
        .to("https://api.twitter.com/2/tweets/search/recent?query=to:BBC")
        .log("This is the status code from the response: ${header.CamelHttpResponseCode}")
        .log("This is the return: ${body}");
  }
}
