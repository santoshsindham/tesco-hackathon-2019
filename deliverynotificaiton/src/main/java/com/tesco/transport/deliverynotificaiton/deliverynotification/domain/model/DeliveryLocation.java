package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryLocation {
    String deliveryId;
    String deliveryPartnerId;
    Location location;
}
