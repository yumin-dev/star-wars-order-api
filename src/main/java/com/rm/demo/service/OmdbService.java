package com.rm.demo.service;

import com.rm.demo.model.EpisodeDetails;
import com.rm.demo.model.OmdbMovieDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OmdbService {
  private static final String ENDPOINT_API = "http://www.omdbapi.com/?apikey=57ec2f6d&i=";
  private static final String ENDPOINT_IMAGE = "http://img.omdbapi.com/?apikey=57ec2f6d&i=";

  @Autowired private RestTemplateBuilder restTemplateBuilder;

  @Cacheable(value = "episodeDetails", sync = true)
  public EpisodeDetails getEpisodeDetails(String imdbId) {
    EpisodeDetails episodeDetails = null;
    if (imdbId != null) {
      System.out.println("======== Cache not found, calling omdb api ========");
      ResponseEntity<OmdbMovieDetailsDto> result =
          restTemplateBuilder
              .build()
              .exchange(ENDPOINT_API + imdbId, HttpMethod.GET, null, OmdbMovieDetailsDto.class);
      OmdbMovieDetailsDto dto = result.getBody();
      episodeDetails = new EpisodeDetails();
      episodeDetails.setImdbId(imdbId);
      episodeDetails.setTitle(dto.getTitle());
      episodeDetails.setReleased(dto.getReleased());
      episodeDetails.setActors(dto.getActors());
      episodeDetails.setDirectors(dto.getDirector());
      episodeDetails.setPlot(dto.getPlot());
      episodeDetails.setCoverUrl(ENDPOINT_IMAGE + imdbId);
    }
    return episodeDetails;
  }
}
