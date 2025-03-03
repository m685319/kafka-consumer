package com.example.consumer.service;

import com.example.consumer.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "my-consumer-group")
    public void listen(ConsumerRecord<String, OrderDTO> record) {
        OrderDTO order = record.value();
        log.info(">> order received successfully: {}", order);
    }
}
