package com.netcracker.edu.api.controller;

import com.netcracker.edu.api.manager.UserManager;
import com.netcracker.edu.api.model.Role;
import com.netcracker.edu.api.model.User;
import com.netcracker.edu.api.model.ui.UiUser;
import com.netcracker.edu.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UiUser uiUser) {
        return userManager.createUser(uiUser);
    }

    @PutMapping("/subscription/{id}")
    public ResponseEntity<User> putReviewById(@PathVariable("id") String id, @RequestBody UiUser uiUser) {
        return userManager.putSubscription(id, uiUser);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<User> putCityByUserId(@PathVariable("id") String id, @RequestBody UiUser uiUser) {
        return userManager.putCity(id, uiUser);
    }

    @GetMapping("/subscription")
    public ResponseEntity<List<User>> findUsersBySubscription(){
        return ResponseEntity.ok(userManager.findUsersBySubscription());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> findRoleByUserId(@PathVariable("id") int id){
        return ResponseEntity.ok(userManager.findRoleByUserId(id));
    }
}
