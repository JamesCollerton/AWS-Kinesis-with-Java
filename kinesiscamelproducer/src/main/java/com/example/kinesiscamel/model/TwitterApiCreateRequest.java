package com.example.kinesiscamel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterApiCreateRequest {
  private List<TwitterApiCreateRule> add;
}
