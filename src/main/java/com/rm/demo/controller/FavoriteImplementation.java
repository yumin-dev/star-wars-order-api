package com.rm.demo.controller;

import com.rm.demo.api.FavoriteApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class FavoriteImplementation implements FavoriteApi {
    @Override
    public ResponseEntity<Void> addFavoriteEpisode(String imdbId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeFavoriteEpisode(String imdbId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
