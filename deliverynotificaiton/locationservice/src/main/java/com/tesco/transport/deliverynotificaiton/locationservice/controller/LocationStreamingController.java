package com.tesco.transport.deliverynotificaiton.locationservice.controller;

import com.tesco.transport.deliverynotificaiton.locationservice.domain.model.DeliveryLocation;
import com.tesco.transport.deliverynotificaiton.locationservice.infrastructure.TrackDeliveryProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class LocationStreamingController {

    @Autowired
    TrackDeliveryProducer trackDeliveryProducer;

    @MessageMapping("/track")
    @SendTo("/track/currentlocation")
    public DeliveryLocation streamLocation(DeliveryLocation deliveryLocation) throws Exception {
        Flux.just(deliveryLocation)
                .flatMap(deliveryPickUp1 -> trackDeliveryProducer.sendMessages(deliveryLocation))
                .map(stringFlux ->  {
                    log.info(stringFlux);
                    return stringFlux;
                }).subscribe();

        return deliveryLocation;
    }
}
