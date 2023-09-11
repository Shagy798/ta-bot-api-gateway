package com.netcracker.edu.api.manager;

import com.netcracker.edu.api.model.Category;
import com.netcracker.edu.api.model.Place;
import com.netcracker.edu.api.model.Rating;
import com.netcracker.edu.api.model.ui.RatingResponse;
import com.netcracker.edu.api.model.ui.UiPlace;
import com.netcracker.edu.api.service.PlaceService;
import com.netcracker.edu.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlaceManager {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private RatingService ratingService;

    public List<RatingResponse> placeByCategory(Category category) {
        List<Place> placeByCategory = placeService.findPlaceByCategory(category);

        Integer[] placeIds = placeByCategory.stream()
                .map(Place::getId)
                .toArray(Integer[]::new);

        List<Rating> ratings = ratingService.findPopularPlace(placeIds);

        List<RatingResponse> ratingResponses = new ArrayList<>();

        Integer[] ratingIds = ratings.stream()
                .map(Rating::getId)
                .toArray(Integer[]::new);

        int counterToRating = 0;
        for (int i = 0; i < placeIds.length; i++) {

            RatingResponse uI = new RatingResponse();

            int number = i + 1;

            if(Arrays.asList(ratingIds).contains(placeIds[i])){
                int ratingId = ratings.get(counterToRating).getId();
                Place findPlace = placeByCategory.stream().filter(place -> ratingId == place.getId()).findFirst().orElse(null);
                uI.setNamePlace(findPlace.getName());
                uI.setAddress(findPlace.getAddress());
                uI.setRatingRatio("(" + ratings.get(counterToRating).getPosscore() + " / " + ratings.get(counterToRating).getNegscore() + ")");
                counterToRating = counterToRating + 1;
            }else{
                uI.setNamePlace(placeByCategory.get(i).getName());
                uI.setAddress(placeByCategory.get(i).getAddress());
                uI.setRatingRatio("Без рейтинга");
            }
            uI.setNumber(number);

            ratingResponses.add(uI);
        }

        return ratingResponses;
    }

    public Place getByAddress(UiPlace uiPlace) {
        return placeService.getByAddress(uiPlace);
    }

    public List<Place> getAll() {
        return placeService.getAll();
    }

    public ResponseEntity<Place> createPlace(UiPlace uiPlace) {
        return placeService.createPlace(uiPlace);
    }

    public void deleteById(int id) {
        placeService.deleteById(id);
    }

    public List<Place> getPlaceIds(Integer[] placeId) {
        return placeService.getPlaceIds(placeId);
    }

    public Place getByName(UiPlace uiPlace) {
        return placeService.getByName(uiPlace);
    }

    public Place getPlaceById(int id) {
        return placeService.getPlaceById(id);
    }
}
