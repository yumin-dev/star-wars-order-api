package com.rm.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class OmdbMovieDetailsDto implements Serializable {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Year")
    String year;
    @JsonProperty("Rated")
    String rated;
    @JsonProperty("Released")
    String released;
    @JsonProperty("Runtime")
    String runtime;
    @JsonProperty("Genre")
    String genre;
    @JsonProperty("Director")
    String director;
    @JsonProperty("Writer")
    String writer;
    @JsonProperty("Actors")
    String actors;
    @JsonProperty("Plot")
    String plot;
    @JsonProperty("Language")
    String language;
    @JsonProperty("Country")
    String country;
    @JsonProperty("Poster")
    String poster;
    @JsonProperty("Ratings")
    List<RatingDto> ratings;
    @JsonProperty("Metascore")
    String metascore;
    String imdbRating;
    String imdbVotes;
    String imdbID;
    @JsonProperty("Type")
    String type;
    @JsonProperty("DVD")
    String dvd;
    @JsonProperty("BoxOffice")
    String boxOffice;
    @JsonProperty("Production")
    String production;
    @JsonProperty("Website")
    String website;
    @JsonProperty("Response")
    String response;
}
