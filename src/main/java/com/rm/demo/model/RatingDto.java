package com.rm.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class RatingDto implements Serializable {
    @JsonProperty("Source")
    String source;
    @JsonProperty("Value")
    String value;
}
