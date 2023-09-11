package com.netcracker.edu.api.model.ui;

import lombok.Data;

@Data
public class UiReview {

    private int id;
    private String review;
    private int authorId;
    private String mark;
    public int placeId;

}
