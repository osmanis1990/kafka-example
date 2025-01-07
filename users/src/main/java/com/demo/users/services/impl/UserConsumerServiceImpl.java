package com.demo.users.services.impl;

import com.demo.users.configurations.CustomObjectMapperConfig;
import com.demo.users.models.User;
import com.demo.users.services.UserConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumerServiceImpl implements UserConsumerService {

    @KafkaListener(topics = {"user-created"}, groupId = "consumer-user-created", topicPartitions = {
            @TopicPartition(topic = "user-created", partitions = {"1"})
    })
    @Override
    public void consume(ConsumerRecord<String, Object> consumerRecord) {
        try {
            User user = CustomObjectMapperConfig.createCustomObjectMapper().readValue(consumerRecord.value().toString(), User.class);
            log.info("User received id: {}", user.getId());
        } catch (Exception e) {
            log.error("Error deserializing message: {}", consumerRecord.value(), e);
        }
    }
}
