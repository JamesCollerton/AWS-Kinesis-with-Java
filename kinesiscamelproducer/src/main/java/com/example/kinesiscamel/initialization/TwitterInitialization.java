package com.example.kinesiscamel.initialization;

import com.example.kinesiscamel.model.TwitterApiSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TwitterInitialization implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${twitter-api.search-url}")
  private String twitterApiSearchUrl;

  @Autowired private RestTemplate restTemplate;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("Searching for tweets");
    ResponseEntity<TwitterApiSearchResult> twitterApiSearchResultResponseEntity =
        restTemplate.exchange(
            String.format("%s%s", twitterApiSearchUrl, "/recent?query=to:bbc"),
            HttpMethod.GET,
            new HttpEntity<>(new HttpHeaders()),
            TwitterApiSearchResult.class);
    log.info("{}", twitterApiSearchResultResponseEntity.getBody());
  }
}
