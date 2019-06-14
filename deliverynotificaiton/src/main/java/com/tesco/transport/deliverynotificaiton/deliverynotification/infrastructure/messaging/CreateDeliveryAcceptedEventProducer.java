package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging;

import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.events.DeliveryPickedUpEvent;
import com.tesco.transport.deliverynotificaiton.deliverynotification.domain.model.DeliveryAccepted;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
public class CreateDeliveryAcceptedEventProducer {

    private static final Logger log = Loggers.getLogger(CreateDeliveryAcceptedEventProducer.class);

    @Autowired
    private CreateDeliveryAcceptedEventConfig kafkaConfig;

    @Autowired
    private KafkaSender<String, String> createDeliveryAcceptedEventSender;

    @Autowired
    MessageHelper messageHelper;

    private static final String CREATE_DELIVERY_PICKED_EVENT = "com.tesco.transport.deliverynotificaiton.deliverynotification.1.0.0";

    public Flux<String> sendMessages(DeliveryAccepted deliveryAcceptedEvent) {
        return Flux.defer(() -> {
            return createDeliveryAcceptedEventSender.send(Mono.just(createSenderRecord(deliveryAcceptedEvent)));
        }).doOnNext(resultData -> log.info(
                "Started publishing delivery notification to kafka topic: {} , for order {}",
                kafkaConfig.getTopic(), resultData.correlationMetadata(), deliveryAcceptedEvent.getDeliveryId()))
                .map(resultData -> processSenderResult(resultData, kafkaConfig.getTopic(), resultData.correlationMetadata()))
                .doOnError(error -> log.error("Error in posting message to topic", error))
                .doOnError(error -> {
                    log.error("Could not publish event ");
                });
    }

    private SenderRecord<String, String, String> createSenderRecord(DeliveryAccepted deliveryPickedUpEvent) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(kafkaConfig.getTopic(),
                deliveryPickedUpEvent.getParcelId(),
                messageHelper.getJsonStringFromObject(deliveryPickedUpEvent));
        log.debug("Delivery event notification received for ", deliveryPickedUpEvent.getDeliveryId());
        return SenderRecord.create(producerRecord, deliveryPickedUpEvent.getDeliveryId());
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
