package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DeliveryEvent {
    @JsonIgnore
    @Version
    private long cas;
    @Id
    String id = UUID.randomUUID().toString();
    String eventType;
    Instant createdDate = Instant.now();
    String deliveryId;
    String parcelId;
}
