package com.rm.demo.controller;

import com.rm.demo.api.FavoriteApi;
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
    String ip = convertLocalhostIp(request.getRemoteAddr());
    List<Favorite> favorites = favoriteRepositories.findByImdbIdAndIp(imdbId, ip);
    if(favorites.isEmpty()) {
      favoriteRepositories.save(new Favorite(imdbId, ip));
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<Boolean> getFavoriteEpisode(String imdbId) {
    String ip = convertLocalhostIp(request.getRemoteAddr());
    List<Favorite> favorites = favoriteRepositories.findByIp(ip);
    return new ResponseEntity<>(favorites.stream().noneMatch(favorite -> favorite.getImdbId().equals(imdbId)), HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<List<String>> getFavoriteEpisodeAll() {
    String ip = convertLocalhostIp(request.getRemoteAddr());
    List<Favorite> favorites = favoriteRepositories.findByIp(ip);
    return new ResponseEntity<>(favorites.stream().map(Favorite::getImdbId).collect(Collectors.toList()), HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<Void> removeFavoriteEpisode(String imdbId) {
    String ip = convertLocalhostIp(request.getRemoteAddr());
    List<Favorite> favorites = favoriteRepositories.findByImdbIdAndIp(imdbId, ip);
    if (!favorites.isEmpty()){
      favoriteRepositories.deleteAll(favorites);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private static String convertLocalhostIp(String originIp){
    if ("0:0:0:0:0:0:0:1".equals(originIp)) return "127.0.0.1";
    return originIp;
  }
}
