package com.tesco.registration.infrastructure.repository;

import com.tesco.registration.domain.entity.User;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCouchbaseRepository extends CouchbaseRepository<User, String> {

    @Query("#{#n1ql.selectEntity} LIMIT 3")
    public List<User> getAvailableUsers();
}
