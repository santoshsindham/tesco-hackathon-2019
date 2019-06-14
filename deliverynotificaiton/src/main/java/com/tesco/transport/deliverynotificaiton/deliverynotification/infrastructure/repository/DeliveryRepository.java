package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.repository;

import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.Delivery;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

public interface DeliveryRepository extends ReactiveCouchbaseRepository<Delivery,String> {
}
