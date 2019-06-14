package com.tesco.transport.deliverynotificaiton.locationservice.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class DeliveryPickUp {
    String deliveryId;
    String parcelId;
    String pickedUserId;
    Instant pickedUpTime;
    Instant estimatedReachTime;
}
