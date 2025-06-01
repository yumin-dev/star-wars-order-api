package com.rm.demo.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepositories extends CrudRepository<Favorite, Long> {
  List<Favorite> findByIp(String ip);

  List<Favorite> findByImdbIdAndIp(String imdbId, String ip);
}
