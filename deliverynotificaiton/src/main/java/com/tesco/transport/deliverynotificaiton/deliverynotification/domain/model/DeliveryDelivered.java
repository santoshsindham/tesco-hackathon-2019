package com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDelivered extends DeliveryEvent {
    String deliveredTo;
    Instant deliveredDate;
}
