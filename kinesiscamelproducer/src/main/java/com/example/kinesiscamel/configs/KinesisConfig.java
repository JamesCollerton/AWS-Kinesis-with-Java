package com.example.kinesiscamel.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@Configuration
public class KinesisConfig {

  @Bean
  public KinesisClient kinesisClient() {
    return KinesisClient.builder().region(Region.EU_WEST_1).build();
  }
}
