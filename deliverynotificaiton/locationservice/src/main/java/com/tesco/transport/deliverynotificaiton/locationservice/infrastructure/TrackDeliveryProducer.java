package com.tesco.transport.deliverynotificaiton.locationservice.infrastructure;

import com.tesco.transport.deliverynotificaiton.locationservice.domain.model.DeliveryLocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

@Component
@Slf4j
public class TrackDeliveryProducer {

    @Autowired
    private TrackDeliveryConfig kafkaConfig;

    @Autowired
    private KafkaSender<String, String> trackDeliverySender;

    @Autowired
    MessageHelper messageHelper;

    private static final String TRACK_DELIVERY_EVENT = "com.tesco.transport.deliverynotificaiton.tracking.1.0.0";

    public Flux<String> sendMessages(DeliveryLocation deliveryLocation) {
        return Flux.defer(() -> {
            return trackDeliverySender.send(Mono.just(createSenderRecord(deliveryLocation)));
        }).doOnNext(resultData -> log.info(
                "Started publishing delivery notification to kafka topic: {} , for order {}",
                kafkaConfig.getTopic(), resultData.correlationMetadata(), deliveryLocation.getDeliveryId()))
                .map(resultData -> processSenderResult(resultData, kafkaConfig.getTopic(), resultData.correlationMetadata()))
                .doOnError(error -> log.error("Error in posting message to topic", error))
                .doOnError(error -> {
                    log.error("Could not publish event ");
                });
    }

    private SenderRecord<String, String, String> createSenderRecord(DeliveryLocation deliveryLocation) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(kafkaConfig.getTopic() ,
                deliveryLocation.getDeliveryId(),
                messageHelper.getJsonStringFromObject(deliveryLocation));
        log.debug("Delivery location event notification received for {}, current location is {}", deliveryLocation.getDeliveryId(),
                deliveryLocation.getLocation().getLatitude() + ":" + deliveryLocation.getLocation().getLongitude());
        return SenderRecord.create(producerRecord, deliveryLocation.toString());
    }

    protected String processSenderResult(SenderResult<String> senderResult, String topicName,
                                         String id) {
        process(senderResult, topicName, id);
        return id;
    }

    private void process(SenderResult<String> senderResult, String topicName, String id) {
        if (null != senderResult.exception()) {
            log.error("Failed to publish message to Topic", senderResult.exception());
            throw new RuntimeException(senderResult.exception());
        }
        log.info(
                "Publishing message with Id: {} to kafka topic: '{}' completed successfully with correlationMetadata: {}",
                id, topicName, senderResult.correlationMetadata());

    }

}
