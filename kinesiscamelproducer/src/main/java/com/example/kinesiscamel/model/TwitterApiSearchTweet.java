package com.example.kinesiscamel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterApiSearchTweet {
  private String id;
  private String text;
}
