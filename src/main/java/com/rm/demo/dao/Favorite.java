package com.rm.demo.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String imdbId;
  private String ip;

  public Favorite() {}

  public Favorite(String imdbId, String ip) {
    this.imdbId = imdbId;
    this.ip = ip;
  }

  public String getImdbId() {
    return imdbId;
  }
}
