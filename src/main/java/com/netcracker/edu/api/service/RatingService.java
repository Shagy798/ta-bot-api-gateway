package com.netcracker.edu.api.service;


import com.netcracker.edu.api.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RatingService {

    @Value("#{'${rating-service-url}' + '${rating-service-rating}'+ '${rating-service-tenbestplace}'}")
    private String ratingServiceUrl;

    @Value("#{'${rating-service-url}' + '${rating-service-rating}'+ '${rating-service-topplaces}'}")
    private String ratingTopPlaceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<Rating> sortTenList() {
        return Arrays.asList(restTemplate.getForObject(ratingServiceUrl, Rating[].class));
    }

    public List<Rating> findPopularPlace(Integer[] placeId) {
        HttpEntity request = new HttpEntity(placeId);
        return Arrays.asList(restTemplate.postForObject(ratingTopPlaceUrl, request, Rating[].class));
    }
}
