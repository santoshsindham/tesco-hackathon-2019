package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.repository;

import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryAccepted;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAcceptedRepository extends ReactiveCouchbaseRepository<DeliveryAccepted,String> {
}
