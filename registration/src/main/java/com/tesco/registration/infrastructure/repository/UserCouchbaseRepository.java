package com.tesco.registration.infrastructure.repository;

import com.tesco.registration.domain.entity.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouchbaseRepository extends CouchbaseRepository<User, String> {

}
