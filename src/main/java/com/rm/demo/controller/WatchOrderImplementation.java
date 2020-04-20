package com.rm.demo.controller;

import com.rm.demo.api.WatchOrderApi;
import com.rm.demo.model.EpisodeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WatchOrderImplementation implements WatchOrderApi {
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResponseEntity<List<EpisodeDetails>> watchOrder(String sort) {
        List<EpisodeDetails> response = new ArrayList<>();
        EpisodeDetails details = new EpisodeDetails();
        details.setImdbId("tt2488496");
        details.setTitle("Star Wars: Episode VII - The Force Awakens");
        details.setDirectors("J.J. Abrams");
        details.setActors("Harrison Ford, Mark Hamill, Carrie Fisher, Adam Driver");
        details.setFavorite(true);
        response.add(details);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
