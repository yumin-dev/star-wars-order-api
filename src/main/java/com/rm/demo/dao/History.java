package com.rm.demo.dao;

import java.sql.Timestamp;
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
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String endpoint;
  private String query;
  private Timestamp time;
  private String ip;

  public History() {}

  public History(String endpoint, String query, Timestamp time, String ip) {
    this.endpoint = endpoint;
    this.query = query;
    this.time = time;
    this.ip = ip;
  }
}
