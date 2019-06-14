package com.tesco.transport.deliverynotificaiton.deliverynotification.application.controller;

import com.tesco.transport.deliverynotificaiton.deliverynotification.application.constants.Endpoints;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.events.DeliveryPickedUpEvent;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.Delivery;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryAccepted;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryPickUp;
import com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging.CreateDeliveryAcceptedEventProducer;
import com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging.CreateDeliveryEventProducer;
import com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.repository.DeliveryAcceptedRepository;
import com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DeliveryController {

    @Autowired
    CreateDeliveryEventProducer createDeliveryEventProducer;

    @Autowired
    DeliveryAcceptedRepository deliveryAcceptedRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    CreateDeliveryAcceptedEventProducer createDeliveryAcceptedEventProducer;

    @RequestMapping(method = RequestMethod.POST, path = {Endpoints.DELIVERY_CREATED})
    public Mono<ResponseEntity<Delivery>> deliveryCreatedEvent(@RequestBody Delivery delivery) {
        return Mono.just(delivery)
                .flatMap(deliveryPickUp1 -> deliveryRepository.save(delivery))
                .map(delivery1 -> ResponseEntity.ok().build());
    }

    @RequestMapping(method = RequestMethod.POST, path = {Endpoints.DELIVERY_PICKEDUP})
    public Mono<ResponseEntity<DeliveryPickUp>> createDeliveryPickedUpEvent(@RequestBody DeliveryPickUp deliveryPickUp) {
        return createDeliveryEventProducer.sendMessages(createDeliveryPickedUpEventRecord(deliveryPickUp)).next()
                .map(stringFlux -> ResponseEntity.ok().build());
    }

    @RequestMapping(method = RequestMethod.POST, path = {Endpoints.DELIVERY_ACCEPTED})
    public Mono<ResponseEntity<DeliveryAccepted>> createDeliveryAccepted(@RequestBody DeliveryAccepted deliveryAccepted) {
        return Mono.just(deliveryAccepted)
                .map(deliveryAccepted1 -> {
                    createDeliveryAcceptedEventProducer.sendMessages(deliveryAccepted1).subscribe();
                    deliveryAccepted1.setId(deliveryAccepted.getDeliveryId() + ":" + deliveryAccepted.getAcceptedUserId());
                    return deliveryAccepted1;
                })
                .flatMap(deliveryPickUp1 -> deliveryAcceptedRepository.save(deliveryPickUp1))
                .map(stringFlux -> ResponseEntity.ok().build());
    }

    private DeliveryPickedUpEvent createDeliveryPickedUpEventRecord(DeliveryPickUp deliveryPickUp) {
        DeliveryPickedUpEvent deliveryPickedUpEvent = new DeliveryPickedUpEvent();
        deliveryPickedUpEvent.setDeliveryPickUp(deliveryPickUp);
        return deliveryPickedUpEvent;
    }
}
