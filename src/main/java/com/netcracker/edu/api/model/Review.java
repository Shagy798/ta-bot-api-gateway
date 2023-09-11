package com.netcracker.edu.api.model;

import lombok.Data;
import java.util.Date;

@Data
public class Review {

    private int id;
    private String review;
    private int authorId;
    private Mark mark;
    private Date dateCreation = new Date();
    private Date dateEdit = null;
    private int placeId;

}

