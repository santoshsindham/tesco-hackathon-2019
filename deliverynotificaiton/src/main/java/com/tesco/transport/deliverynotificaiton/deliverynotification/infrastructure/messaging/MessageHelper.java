package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
public class MessageHelper {
    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = Loggers.getLogger(MessageHelper.class);

    public String getJsonStringFromObject(Object object) {
        String message = "";
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            message = objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
            logger.error("Error while creating object to JSON");
        }
        return message;
    }
}
