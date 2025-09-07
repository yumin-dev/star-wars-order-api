package com.rm.demo.controller;

import com.rm.demo.api.WatchOrderApi;
import com.rm.demo.model.EpisodeDetails;
import com.rm.demo.service.StarWarsOrderService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Implementation of WatchOrderApi for Star Wars episode ordering.
 */
@Controller
public final class WatchOrderImplementation implements WatchOrderApi {
  private static final Logger logger = LoggerFactory.getLogger(WatchOrderImplementation.class);
  private final StarWarsOrderService starWarsOrderService;

  public WatchOrderImplementation(StarWarsOrderService starWarsOrderService) {
    this.starWarsOrderService = starWarsOrderService;
  }

  /**
   * Returns a sorted list of Star Wars episodes based on the provided sort type.
   * @param sort the sorting criteria
   * @return ResponseEntity containing the sorted list or error status
   */
  @Override
  public ResponseEntity<List<EpisodeDetails>> watchOrder(String sort) {
    try {
      List<EpisodeDetails> response = starWarsOrderService.getSortedStarWarEpisodes(sort);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Service error: ", e);
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
