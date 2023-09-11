package com.netcracker.edu.api.model;

import lombok.Data;
import java.util.Date;

@Data
public class User {

    private int id;
    private Role role;
    private String name;
    private Date registrationDate;
    private boolean subscription;
    private String city;

}

