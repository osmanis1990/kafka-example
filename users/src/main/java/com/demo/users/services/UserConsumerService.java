package com.demo.users.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface UserConsumerService {

    void consume(ConsumerRecord<String, Object> record) throws JsonProcessingException;
}
