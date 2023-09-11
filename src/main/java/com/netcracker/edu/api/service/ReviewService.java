package com.netcracker.edu.api.service;

import com.netcracker.edu.api.model.Review;
import com.netcracker.edu.api.model.ui.UiReview;
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
public class ReviewService {

    @Value("#{'${review-service-url}' +'${review-service-review}' + '${review-service-author}'}")
    private String reviewServiceUrlFindByAuthorId;

    @Value("#{'${review-service-url}'+ '${review-service-review}' + '${review-service-place}'}")
    private String reviewServiceUrlFindByPlaceId;

    @Value("#{'${review-service-url}'+ '${review-service-review}' + '${review-service-place}'}")
    private String reviewServiceUrlFindByPlaceIdAndAuthorId;

    @Value("#{'${review-service-url}' + '${review-service-review}'}")
    private String reviewServiceUrl;

    @Value("#{'${review-service-url}' +  '${review-service-review}'+ '${review-service-place}'+'${review-service-mark}'}")
    private String reviewServiceMarkId;

    @Autowired
    private RestTemplate restTemplate;

    public List<Review> getById(String id) {
        String url = reviewServiceUrl + "/{id}";
        return Arrays.asList(restTemplate.getForObject(url, Review[].class, id));
    }

    public List<Review> getByAuthorId(int authorId, int page) {
        String url = reviewServiceUrlFindByAuthorId + "/{authorId}" + "/{page}";
        return Arrays.asList(restTemplate.getForObject(url, Review[].class, authorId, page));
    }

    public List<Review> getByPlaceId(int placeId, int page) {
        String url = reviewServiceUrlFindByPlaceId + "/{placeId}" + "/{page}";
        return Arrays.asList(restTemplate.getForObject(url, Review[].class, placeId, page));
    }

    public List<Review> getByMarkIdAndPlaceId(int markId, int placeId, int page) {
        String url = reviewServiceMarkId + "/{markId}" + "/{placeId}" + "/{page}";
        return Arrays.asList(restTemplate.getForObject(url, Review[].class, markId, placeId, page));
    }

    public List<Review> getByPlaceIdAndAuthorId(int placeId, int authorId, int page) {
        String url = reviewServiceUrlFindByPlaceIdAndAuthorId + "/{placeId}" + "/{authorId}" + "/{page}";
        return Arrays.asList(restTemplate.getForObject(url, Review[].class, placeId, authorId, page));
    }

    public ResponseEntity<Review> putReview(String id, UiReview review) {
        String url = reviewServiceUrl + "/{id}";
        HttpEntity request = new HttpEntity(review);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Review.class, id);
    }

    public void deleteById(String id) {
        String url = reviewServiceUrl + "/{id}";
        restTemplate.delete(url, id);
    }

    public ResponseEntity<Review> createReview(UiReview review) {
        HttpEntity request = new HttpEntity(review);
        return restTemplate.exchange(reviewServiceUrl, HttpMethod.POST, request, Review.class);
    }

}

