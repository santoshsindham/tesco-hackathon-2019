package com.tesco.registration.domain.repository;

import com.tesco.registration.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    public User save(User user);

    public Optional<User> getUser(String userId);

    public List<User> getAvailableUsers();
}
