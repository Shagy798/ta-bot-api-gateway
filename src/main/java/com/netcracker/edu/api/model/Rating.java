package com.netcracker.edu.api.model;

import lombok.Data;

@Data
public class Rating {

    private int id;
    private int sum;
    private int number;
    private int posscore;
    private int negscore;
    private double average;

}
