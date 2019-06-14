package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CreateDeliveryPickedEventConfig extends BaseKafkaConfig {
    @Value("#{${create-delivery-pickedconfig}}")
    private Map<String, String> kafkaConfig;

    @Getter
    @Setter
    @Value("${kafka.delivery.picked.topic}")
    private String topic;

    @Bean
    @Primary
    public KafkaSender<String, String> createDeliveryPickedSender() {
        Map<String, Object> props = new HashMap<>();
        populateSenderConfigs(props, kafkaConfig);
        populateSslConfigs(props);
        populateSaslConfigs(props, kafkaConfig);
        SenderOptions<String, String> senderOptions = SenderOptions.create(props);
        return KafkaSender.create(senderOptions);
    }
}
