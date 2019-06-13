package com.tesco.registration.application.controller;

import com.tesco.registration.application.constants.QueryEndpoints;
import com.tesco.registration.domain.entity.User;
import com.tesco.registration.domain.handler.UserRegistrationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(QueryEndpoints.USER)
public class RegistrationController {

    @Autowired
    private UserRegistrationHandler userRegistrationHandler;

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable String userId) {

        return userRegistrationHandler.getUser(userId);
    }

    @PostMapping(QueryEndpoints.USER_REGISTRATION_URL)
    public User registerUser(@RequestBody User user) {

        return userRegistrationHandler.save(user);
    }
}
