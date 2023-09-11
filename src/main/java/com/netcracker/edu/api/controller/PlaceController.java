package com.netcracker.edu.api.controller;

import com.netcracker.edu.api.manager.PlaceManager;
import com.netcracker.edu.api.model.*;
import com.netcracker.edu.api.model.ui.RatingResponse;
import com.netcracker.edu.api.model.ui.UiPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PlaceController {

    @Autowired
    private PlaceManager placeManager;

    @PostMapping("/place/address")
    public ResponseEntity<Place> getPlaceByAddress(@RequestBody UiPlace uiPlace) {
        return ResponseEntity.ok(placeManager.getByAddress(uiPlace));
    }

    @PostMapping("/place/name")
    public ResponseEntity<Place> getPlaceByName(@RequestBody UiPlace uiPlace) {
        return ResponseEntity.ok(placeManager.getByName(uiPlace));
    }

    @GetMapping("/place")
    public ResponseEntity<List<Place>> getAll() {
        return ResponseEntity.ok(placeManager.getAll());
    }

    @PostMapping("/place")
    public ResponseEntity<Place> createUser(@RequestBody UiPlace uiPlace) {
        return placeManager.createPlace(uiPlace);
    }

    @PostMapping("/place/category")
    public ResponseEntity <List<RatingResponse>> findPopularPlace(@RequestBody Category category) {
       return ResponseEntity.ok(placeManager.placeByCategory(category));
    }

    @PostMapping("/place/placeid")
    public ResponseEntity <List<Place>> findPlaceById(@RequestBody Integer[] placeId){
        return ResponseEntity.ok(placeManager.getPlaceIds(placeId));
    }

    @GetMapping("/place/placeId/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") int id) {
        return ResponseEntity.ok(placeManager.getPlaceById(id));
    }

    @DeleteMapping("/place/{id}")
    public ResponseEntity<HttpStatus> deletePlaceById(@PathVariable("id") int id) {
        placeManager.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
