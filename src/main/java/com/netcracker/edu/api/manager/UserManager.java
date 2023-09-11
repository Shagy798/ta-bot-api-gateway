package com.netcracker.edu.api.manager;

import com.netcracker.edu.api.model.Role;
import com.netcracker.edu.api.model.User;
import com.netcracker.edu.api.model.ui.UiUser;
import com.netcracker.edu.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserService userService;

    public ResponseEntity<User> createUser(UiUser uiUser) {
        return userService.createUser(uiUser);
    }

    public ResponseEntity<User> putSubscription(String id, UiUser uiUser) {
        return userService.putSubscription(id, uiUser);
    }

    public ResponseEntity<User> putCity(String id, UiUser uiUser) {
        return userService.putCity(id, uiUser);
    }

    public List<User> findUsersBySubscription() {
        return userService.findUsersBySubscription();
    }

    public Role findRoleByUserId(int id){return userService.findRoleByUserId(id);}
}
