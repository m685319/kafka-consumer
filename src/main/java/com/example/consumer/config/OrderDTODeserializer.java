package com.example.consumer.config;

import com.example.consumer.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderDTODeserializer implements Deserializer<OrderDTO> {

    private final ObjectMapper objectMapper;

    public OrderDTODeserializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public OrderDTO deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, OrderDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing OrderDTO", e);
        }
    }
}