package com.netcracker.edu.api.model.ui;

import lombok.Data;

@Data
public class ReviewResponse {

    private int id;
    private int mark;
    private String review;
    private String namePlace;

}
