package com.tesco.registration.domain.handler;

import com.tesco.registration.domain.entity.User;
import com.tesco.registration.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRegistrationHandler {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {

        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User getUser(String userId) {

        return userRepository.getUser(userId).get();
    }
}
