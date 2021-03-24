package com.example.kinesiscamel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwitterConfig {

  @Value("${twitter-api.bearer-token}")
  private String bearerToken;
}
