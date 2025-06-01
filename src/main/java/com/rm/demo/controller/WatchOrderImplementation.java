package com.rm.demo.controller;

import com.rm.demo.api.WatchOrderApi;
import com.rm.demo.model.EpisodeDetails;
import com.rm.demo.service.StarWarsOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class WatchOrderImplementation implements WatchOrderApi {
  @Autowired private StarWarsOrderService starWarsOrderService;

  @Override
  public ResponseEntity<List<EpisodeDetails>> watchOrder(String sort) {
    try {
      List<EpisodeDetails> response = starWarsOrderService.getSortedStarWarEpisodes(sort);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("==> Service error: " + e);
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
