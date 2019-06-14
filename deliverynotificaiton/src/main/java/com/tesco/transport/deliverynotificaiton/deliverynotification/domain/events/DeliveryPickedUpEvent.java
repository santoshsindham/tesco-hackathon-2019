package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.events;

import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryAddress;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryPickUp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryPickedUpEvent {
    DeliveryPickUp deliveryPickUp;
    DeliveryAddress deliveryAddress;
}
