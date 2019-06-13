package com.tesco.registration.infrastructure.repository;

import com.tesco.registration.domain.entity.User;
import com.tesco.registration.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserCouchbaseRepository userCouchbaseRepository;

    @Override
    public User save(User user) {
        return userCouchbaseRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userCouchbaseRepository.findById(userId);
    }

    @Override
    public List<User> getAvailableUsers() {
        return userCouchbaseRepository.getAvailableUsers();
    }
}
