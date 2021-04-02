package com.example.kinesiscamel.configs;

import com.example.kinesiscamel.models.TwitterResponseItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class ConsumerConfig {

    @Bean
    public Consumer<String> twitterResponseItemConsumer() {
        return twitterResponseItem -> log.info("Received {}", twitterResponseItem);
    }

}
