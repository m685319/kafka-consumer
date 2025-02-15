package com.example.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test", groupId = "my-consumer-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Получено сообщение: " + record.value());
    }


}
