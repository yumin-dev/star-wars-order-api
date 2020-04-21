package com.rm.demo.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepositories extends CrudRepository<Favorite, Long> {
    List<Favorite> findByIp(String ip);
    List<Favorite> findByImdbIdAndIp(String imdbId, String ip);
}
