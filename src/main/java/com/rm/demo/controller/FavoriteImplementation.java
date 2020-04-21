package com.rm.demo.controller;

import com.rm.demo.api.FavoriteApi;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rm.demo.dao.Favorite;
import com.rm.demo.dao.FavoriteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FavoriteImplementation implements FavoriteApi {
  @Autowired
  private FavoriteRepositories favoriteRepositories;
  @Autowired
  private HttpServletRequest request;

  @Override
  @Transactional
  public ResponseEntity<Void> addFavoriteEpisode(String imdbId) {
    String ip = request.getRemoteAddr();
    List<Favorite> favorites = favoriteRepositories.findByIp(ip);
    if(favorites.size()==0) {
      favoriteRepositories.save(new Favorite(imdbId, ip));
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<Boolean> getFavoriteEpisode(String imdbId) {
    String ip = request.getRemoteAddr();
    List<Favorite> favorites = favoriteRepositories.findByIp(ip);
    favorites.stream().filter(favorite -> favorite.getImdbId()==imdbId).collect(Collectors.toList());
    return new ResponseEntity<>(favorites.stream().filter(favorite -> favorite.getImdbId()==imdbId).collect(Collectors.toList()).size()>0, HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<List<String>> getFavoriteEpisodeAll() {
    String ip = request.getRemoteAddr();
    List<Favorite> favorites = favoriteRepositories.findByIp(ip);
    return new ResponseEntity<>(favorites.stream().map(favorite -> favorite.getImdbId()).collect(Collectors.toList()), HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<Void> removeFavoriteEpisode(String imdbId) {
    String ip = request.getRemoteAddr();
    List<Favorite> favorites = favoriteRepositories.findByImdbIdAndIp(imdbId, ip);
    if (favorites.size()>0){
      favoriteRepositories.deleteAll(favorites);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
