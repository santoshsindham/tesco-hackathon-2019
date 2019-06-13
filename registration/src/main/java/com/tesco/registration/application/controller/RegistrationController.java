package com.tesco.registration.application.controller;

import com.tesco.registration.application.constants.QueryEndpoints;
import com.tesco.registration.domain.entity.User;
import com.tesco.registration.domain.handler.UserRegistrationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(QueryEndpoints.USER_REGISTRATION_URL)
public class RegistrationController {

    @Autowired
    private UserRegistrationHandler userRegistrationHandler;

    @PostMapping
    public User registerUser(@RequestBody User user) {

        return userRegistrationHandler.save(user);
    }
}
