package com.netcracker.edu.api.model;

import lombok.Data;

@Data
public class Place {

    private int id;
    private String city;
    private String address;
    private Category category;
    private String name;

}
