package com.rm.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RatingDto implements Serializable {
  @JsonProperty("Source")
  String source;

  @JsonProperty("Value")
  String value;
}
