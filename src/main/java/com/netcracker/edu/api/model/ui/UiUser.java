package com.netcracker.edu.api.model.ui;

import lombok.Data;

@Data
public class UiUser {

    private String id;
    private String role;
    private String name;
    private boolean subscription;
    private String city;

}
