package com.netcracker.edu.api.service;

import com.netcracker.edu.api.model.Review;
import com.netcracker.edu.api.model.Role;
import com.netcracker.edu.api.model.User;
import com.netcracker.edu.api.model.ui.UiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Value("#{'${user-service-url}' + '${user-service-user}'+'${user-service-register}'}")
    private String userServiceUrlRegistration;

    @Value("#{'${user-service-url}' + '${user-service-user}'+'${user-service-subscription}'}")
    private String userServiceUrlSubcription;

    @Value("#{'${user-service-url}' + '${user-service-user}'}")
    private String userServiceUrl;

    @Value("#{'${user-service-url}' + '${user-service-user}'+ '${user-service-city}'}")
    private String userServiceUrlCity;

    @Value("#{'${user-service-url}' + '${user-service-user}' + '${user-service-role}'}")
    private String userServiceUrlRole;

    @Autowired
    private RestTemplate restTemplate;

    public User getById(String id) {
        String url = userServiceUrl + "/{id}";
        return restTemplate.getForObject(url, User.class, id);
    }

    public ResponseEntity<User> createUser(UiUser uiUser) {
        HttpEntity request = new HttpEntity(uiUser);
        return restTemplate.exchange(userServiceUrlRegistration, HttpMethod.POST, request, User.class);
    }

    public ResponseEntity<User> putSubscription(String id, UiUser uiUser) {
        String url = userServiceUrlSubcription + "/{id}";
        HttpEntity request = new HttpEntity(uiUser);
        return restTemplate.exchange(url, HttpMethod.PUT, request, User.class, id);
    }

    public ResponseEntity<User> putCity(String id, UiUser uiUser) {
        String url = userServiceUrlCity + "/{id}";
        HttpEntity request = new HttpEntity(uiUser);
        return restTemplate.exchange(url, HttpMethod.PUT, request, User.class, id);
    }

    public List<User> findUsersBySubscription() {
        String url = userServiceUrlSubcription;
        return Arrays.asList(restTemplate.getForObject(url, User[].class));
    }

    public Role findRoleByUserId(int id) {
        String url = userServiceUrlRole+"/{id}";
        return restTemplate.getForObject(url, Role.class, id);
    }
}
