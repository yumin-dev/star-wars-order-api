package com.rm.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StarWarsOrderDto {
  String imdbId;
  PositionDto position;
}
