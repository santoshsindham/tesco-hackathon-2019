package com.tesco.registration.domain.repository;

import com.tesco.registration.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public User save(User user);
}
