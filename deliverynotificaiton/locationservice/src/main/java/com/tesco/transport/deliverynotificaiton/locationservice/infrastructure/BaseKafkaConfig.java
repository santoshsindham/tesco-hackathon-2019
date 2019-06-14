package com.tesco.transport.deliverynotificaiton.locationservice.infrastructure;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class BaseKafkaConfig {
    @Value("${kafka.ssl.protocol}")
    private String protocol;

    @Value("${kafka.truststore.location}")
    private String trustStoreLocation;

    @Value("${kafka.truststore.password}")
    private String trustStorePassword;

    @Value("${kafka.keystore.location}")
    private String keyStoreLocation;

    @Value("${kafka.ssl.keystore.password}")
    private String keyStorePassword;

    @Value("${kafka.ssl.key.password}")
    private String sslKeyPassword;

    @Value("${kafka.ssl.enabled}")
    private Boolean kafkaSslEnabled;


    protected Map<String, Object> populateSenderConfigs(Map<String, Object> props, Map<String, String> kafkaConfig) {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.get("kafka.hosts"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaConfig.get("kafka.timeout"));
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaConfig.get("batch.size"));
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaConfig.get("linger.ms"));
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaConfig.get("buffer.memory"));
        props.put(ProducerConfig.ACKS_CONFIG, kafkaConfig.get("kafka.acks"));
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaConfig.get("kafka.retries"));
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, kafkaConfig.get("kafka.max-inflight"));
        return props;
    }

    protected Map<String, Object> populateSaslConfigs(Map<String, Object> props, Map<String, String> kafkaConfig) {
//        props.put(SaslConfigs.SASL_MECHANISM, kafkaConfig.get("sasl.mechanism"));
//        props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaConfig.get("sasl.jaas.com.tesco.transport.deliverynotificaiton.locationservice.config"));
//        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaConfig.get("security.protocol"));
        return props;
    }

    protected Map<String, Object> populateSslConfigs(Map<String, Object> props) {
        if (kafkaSslEnabled) {
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, protocol);
            props.put(SslConfigs.SSL_PROTOCOL_CONFIG, "TLS");
            props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
            props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
            props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreLocation);
            props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keyStorePassword);
            props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, sslKeyPassword);
            props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
        }
        return props;
    }

    protected Map<String, Object> populateReceiverConfigs(Map<String, Object> props, Map<String, String> kafkaConfig) {

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.get("kafka.hosts"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.get("group.id"));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.get("offset.reset"));
        return props;
    }
}
