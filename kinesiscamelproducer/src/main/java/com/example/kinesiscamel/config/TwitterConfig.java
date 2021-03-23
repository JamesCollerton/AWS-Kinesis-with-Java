package com.example.kinesiscamel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class TwitterConfig {

  @Value("${twitter-api.bearer-token}")
  private String bearerToken;

  @Bean
  public RestTemplate getRestTemplate(
      @Value("${twitter-api.bearer-token}") final String bearerToken) {
    log.info(bearerToken);
    return new RestTemplateBuilder(
            rt ->
                rt.getInterceptors()
                    .add(
                        (request, body, execution) -> {
                          request
                              .getHeaders()
                              .add("Authorization", String.format("Bearer %s", bearerToken));
                          return execution.execute(request, body);
                        }))
        .build();
  }
}
