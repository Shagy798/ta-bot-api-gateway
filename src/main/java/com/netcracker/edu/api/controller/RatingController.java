package com.netcracker.edu.api.controller;

import com.netcracker.edu.api.manager.RatingManager;
import com.netcracker.edu.api.model.Rating;
import com.netcracker.edu.api.model.ui.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RatingController {

    @Autowired
    private RatingManager ratingManager;

    @GetMapping("/rating/tenbestplace")
    public ResponseEntity <List<RatingResponse>> findTopPlace() {
        return ResponseEntity.ok(ratingManager.ratingToString());
    }

}
