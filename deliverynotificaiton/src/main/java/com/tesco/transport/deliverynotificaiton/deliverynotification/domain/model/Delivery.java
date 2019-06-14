package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Delivery {
    @JsonIgnore
    @Version
    private long cas;
    @Id
    private String id;
    String deliveryId;
    String parcelId;
    List<DeliveryEvent> deliveryEvents;
}
