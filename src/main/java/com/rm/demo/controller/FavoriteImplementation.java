package com.rm.demo.controller;

import com.rm.demo.api.FavoriteApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteImplementation implements FavoriteApi {
    @Override
    public ResponseEntity<Void> addFavoriteEpisode(String imdbId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> getFavoriteEpisode(String imdbId) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getFavoriteEpisodeAll() {
        List<String> response = new ArrayList<>();
        response.add("tt2488496");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeFavoriteEpisode(String imdbId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
