package com.example.kinesiscamel.initialization;

import com.example.kinesiscamel.model.TwitterApiSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class TwitterInitialization implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${twitter-api.search-url}")
  private String twitterApiSearchUrl;

  @Value("${twitter-api.recent-path}")
  private String twitterApiRecentPath;

  @Autowired private RestTemplate restTemplate;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("Searching for tweets, composing URI");

    String uriString =
        UriComponentsBuilder.fromHttpUrl(
                String.format("%s%s", twitterApiSearchUrl, twitterApiRecentPath))
            .queryParam("query", "to:bbc")
            .toUriString();

    log.info("Composed URI {}", uriString);

    ResponseEntity<TwitterApiSearchResult> twitterApiSearchResultResponseEntity =
        restTemplate.getForEntity(uriString, TwitterApiSearchResult.class);

    log.info("{}", twitterApiSearchResultResponseEntity.getBody());
  }
}
