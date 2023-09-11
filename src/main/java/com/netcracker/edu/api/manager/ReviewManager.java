package com.netcracker.edu.api.manager;

import com.netcracker.edu.api.model.Place;
import com.netcracker.edu.api.model.Review;
import com.netcracker.edu.api.model.ui.ReviewResponse;
import com.netcracker.edu.api.model.ui.UiReview;
import com.netcracker.edu.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewManager {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PlaceManager placeManager;

    public ResponseEntity<Review> createReview(UiReview uiReviewUpdated) {
        return reviewService.createReview(uiReviewUpdated);
    }

    public List<ReviewResponse> getByAuthorId(int authorId, int page) {

        List<Review> reviews = reviewService.getByAuthorId(authorId, page);

        Integer[] placeIds = reviews.stream()
                .map(Review::getPlaceId)
                .toArray(Integer[]::new);

        List<Place> places = placeManager.getPlaceIds(placeIds);
        List<ReviewResponse> reviewResponse = new ArrayList<>();

        for (int i = 0; i < placeIds.length; i++) {

            int reviewId = placeIds[i];

            Place findPlace = places.stream().filter(place -> reviewId == place.getId()).findFirst().orElse(null);

            ReviewResponse uI = new ReviewResponse();

            uI.setId(reviews.get(i).getId());
            uI.setMark(reviews.get(i).getMark().getId());
            uI.setReview(reviews.get(i).getReview());
            uI.setNamePlace(findPlace.getName());

            reviewResponse.add(uI);
        }
        return reviewResponse;
    }

    public List<Review> getByPlaceId(int placeId, int page) {
        return reviewService.getByPlaceId(placeId, page);
    }

    public List<Review> getByMarkIdAndPlaceId(int markId, int placeId, int page) {
        return reviewService.getByMarkIdAndPlaceId(markId, placeId, page);
    }

    public List<Review> getByPlaceIdAndAuthorId(int placeId, int authorId, int page) {
        return reviewService.getByPlaceIdAndAuthorId(placeId, authorId, page);
    }

    public ResponseEntity<Review> putReview(String id, UiReview uiReviewUpdated) {
        return reviewService.putReview(id, uiReviewUpdated);
    }

    public void deleteById(String id) {
        reviewService.deleteById(id);
    }
}
