package com.netcracker.edu.api.manager;

import com.netcracker.edu.api.model.Place;
import com.netcracker.edu.api.model.Rating;
import com.netcracker.edu.api.model.ui.RatingResponse;
import com.netcracker.edu.api.service.PlaceService;
import com.netcracker.edu.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RatingManager {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private PlaceService placeService;

    public List<RatingResponse> ratingToString() {
        List<Rating> ratings = ratingService.sortTenList();

        Integer[] placeIds = ratings.stream()
                .map(Rating::getId)
                .toArray(Integer[]::new);

        List<Place> places = placeService.getPlaceIds(placeIds);

        List<RatingResponse> ratingResponses = new ArrayList<>();

        for (int i = 0; i < placeIds.length; i++) {

            //places.indexOf(ratings.get(i).getId());
            int placeId = placeIds[i];

            Place findPlace = places.stream().filter(place -> placeId == place.getId()).findFirst().orElse(null);
            RatingResponse uI = new RatingResponse();
            int number = i + 1;
            uI.setNumber(number);
            uI.setNamePlace(findPlace.getName());
            uI.setAddress(findPlace.getAddress());
            uI.setRatingRatio("(" + ratings.get(i).getPosscore() + " / " + ratings.get(i).getNegscore() + ")");

            ratingResponses.add(uI);
        }
        return ratingResponses;
    }

    public List<Rating> findPopularPlace(Integer[] placeId) {
        return ratingService.findPopularPlace(placeId);
    }
}
