package com.tesco.transport.deliverynotificaiton.locationservice.infrastructure;

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
public class TrackDeliveryConfig extends BaseKafkaConfig {
    @Value("#{${track-delivery-pickedconfig}}")
    private Map<String, String> kafkaConfig;

    @Getter
    @Setter
    @Value("${kafka.delivery.tracking.topic}")
    private String topic;

    @Bean("deliveryTrackerSender")
    @Primary
    public KafkaSender<String, String> deliveryTrackerSender() {
        Map<String, Object> props = new HashMap<>();
        populateSenderConfigs(props, kafkaConfig);
        populateSslConfigs(props);
        populateSaslConfigs(props, kafkaConfig);
        SenderOptions<String, String> senderOptions = SenderOptions.create(props);
        return KafkaSender.create(senderOptions);
    }
}
