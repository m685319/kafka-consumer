package com.example.consumer.service;

import com.example.consumer.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "test", groupId = "my-consumer-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            OrderDTO order = objectMapper.readValue(record.value(), OrderDTO.class);
            System.out.println("Получен заказ: " + order);
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка десериализации: " + e.getMessage());
        }
        System.out.println("Получено сообщение: " + record.value());
    }
}
