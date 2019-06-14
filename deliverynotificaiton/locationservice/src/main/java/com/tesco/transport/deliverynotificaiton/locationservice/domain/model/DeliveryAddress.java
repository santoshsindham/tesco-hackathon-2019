package com.tesco.transport.deliverynotificaiton.locationservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryAddress {
    String doorNo;
    String apartment;
    String street;
    String city;
    String state;
    String country;
    String zip;
}
