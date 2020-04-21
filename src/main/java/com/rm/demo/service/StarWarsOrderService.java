package com.rm.demo.service;

import com.rm.demo.model.EpisodeDetails;
import com.rm.demo.model.StarWarsOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarWarsOrderService {
    private static final String ENDPOINT_API = "https://mysterious-peak-27876.herokuapp.com/";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private OmdbService omdbService;

    public List<EpisodeDetails> getSortedStarWarEpisodes(String sort){
        List<EpisodeDetails> result = null;
        ResponseEntity<List<StarWarsOrderDto>> serviceResponse = restTemplateBuilder.build().exchange(ENDPOINT_API, HttpMethod.GET, null, new ParameterizedTypeReference<List<StarWarsOrderDto>>(){});

        SortType sortType = SortType.getSortType(sort);
        List<StarWarsOrderDto> sortedStoryList = null;
        switch (sortType){
            case MACHETE:
                sortedStoryList = serviceResponse.getBody().stream().filter(story->story.getPosition().getMachete()!=null)
                        .sorted(Comparator.comparingInt(s -> s.getPosition().getMachete())).collect(Collectors.toList());
                break;
            case RELEASE:
                sortedStoryList = serviceResponse.getBody().stream().filter(story->story.getPosition().getRelease()!=null)
                        .sorted(Comparator.comparingInt(s -> s.getPosition().getRelease())).collect(Collectors.toList());
                break;
            default:
                sortedStoryList = serviceResponse.getBody().stream().filter(story->story.getPosition().getEpisode()!=null)
                        .sorted(Comparator.comparingInt(s -> s.getPosition().getEpisode())).collect(Collectors.toList());
        }

        return sortedStoryList.stream().map(story->omdbService.getEpisodeDetails(story.getImdbId())).collect(Collectors.toList());
    }

    enum SortType {
        EPISODE,
        RELEASE,
        MACHETE;

        static SortType getSortType(String sort){
            try {
                return SortType.valueOf(sort.toUpperCase());
            } catch (Exception e) {
                return EPISODE;
            }
        }
    }
}
