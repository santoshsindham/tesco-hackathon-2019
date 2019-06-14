package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class DeliveryPickUp extends DeliveryEvent{
    String pickedUserId;
    Instant pickedUpTime;
    Instant estimatedReachTime;
}
