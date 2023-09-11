package com.netcracker.edu.api.service;

import com.netcracker.edu.api.model.*;
import com.netcracker.edu.api.model.ui.UiPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlaceService {

    @Value("#{'${place-service-url}' + '${place-service-place}'}")
    private String placeServiceUrl;

    @Value("#{'${place-service-url}' + '${place-service-place}'+ '${place-service-category}'}")
    private String placeCategoryUrl;

    @Autowired
    private RestTemplate restTemplate;

/*
    public Place getById(String id) {
        String url = placeServiceUrl + "/{id}";
        return restTemplate.getForObject(url, Place.class, id);
    }*/

    public Place getByAddress(UiPlace uiPlace) {
        String url = placeServiceUrl + "/address";
        return restTemplate.getForObject(url, Place.class, uiPlace);
    }

    public List<Place> getAll() {
        return Arrays.asList(restTemplate.getForObject(placeServiceUrl, Place[].class));
    }

    public ResponseEntity<Place> createPlace(UiPlace uiPlace) {
        HttpEntity request = new HttpEntity(uiPlace);
        return restTemplate.exchange(placeServiceUrl, HttpMethod.POST, request, Place.class);
    }

    public List<Place> findPlaceByCategory(Category category) {
        HttpEntity request = new HttpEntity(category);
        return Arrays.asList(restTemplate.postForObject(placeCategoryUrl, request, Place[].class));
    }

    public void deleteById(int id) {
        String url = placeServiceUrl + "/{id}";
        restTemplate.delete(url, id);
    }

    public List<Place> getPlaceIds(Integer[] placeId) {
        HttpEntity request = new HttpEntity(placeId);
        String url = placeServiceUrl + "/placeId";
        return Arrays.asList(restTemplate.postForObject(url, request, Place[].class));
    }

    public Place getByName(UiPlace uiPlace) {
        String url = placeServiceUrl + "/name";
        return restTemplate.postForObject(url, uiPlace, Place.class);
    }

    public Place getPlaceById(int id) {
        String url = placeServiceUrl + "/placeId" + "/{id}";
        return restTemplate.getForObject(url, Place.class, id);
    }
}
