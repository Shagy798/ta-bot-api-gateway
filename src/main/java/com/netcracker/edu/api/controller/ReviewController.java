package com.netcracker.edu.api.controller;

import com.netcracker.edu.api.manager.ReviewManager;
import com.netcracker.edu.api.model.Review;
import com.netcracker.edu.api.model.ui.ReviewResponse;
import com.netcracker.edu.api.model.ui.UiReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    @Autowired
    private ReviewManager reviewManager;

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody UiReview uiReviewUpdated) {
        return reviewManager.createReview(uiReviewUpdated);
    }

    @GetMapping("/author/{authorId}/{page}")
    public ResponseEntity<List<ReviewResponse>> getEmployeeByAuthorId(@PathVariable("authorId") int authorId, @PathVariable("page") int page) {
        return ResponseEntity.ok(reviewManager.getByAuthorId(authorId, page));
    }

    @GetMapping("/place/{placeId}/{page}")
    public ResponseEntity<List<Review>> getEmployeeByPlaceId(@PathVariable("placeId") int placeId, @PathVariable("page") int page) {
        return ResponseEntity.ok(reviewManager.getByPlaceId(placeId, page));
    }

    @GetMapping("/mark/place/{markId}/{placeId}/{page}")
    public ResponseEntity<List<Review>> getEmployeeByMarkIdAndPlaceId(@PathVariable("markId") int markId, @PathVariable("placeId") int placeId, @PathVariable("page") int page) {
        return ResponseEntity.ok(reviewManager.getByMarkIdAndPlaceId(markId, placeId, page));
    }

    @GetMapping("/place/{placeId}/{authorId}/{page}")
    public ResponseEntity<List<Review>> findReviewByPlaceIdAndAuthorId(@PathVariable("placeId") int placeId, @PathVariable("authorId") int authorId, @PathVariable("page") int page) {
        return ResponseEntity.ok(reviewManager.getByPlaceIdAndAuthorId(placeId, authorId, page));
    }


    @PutMapping("/review/{id}")
    public ResponseEntity<Review> putReviewById(@PathVariable("id") String id, @RequestBody UiReview uiReviewUpdated) {
        return reviewManager.putReview(id, uiReviewUpdated);

    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<HttpStatus> deleteReviewById(@PathVariable("id") String id) {
        reviewManager.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
