package com.busapp.user.messagebroker.producer.impl;

import com.busapp.user.messagebroker.model.ProfileUpdateRequest;
import com.busapp.user.messagebroker.producer.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.response.traveller-update}")
    private String profileUpdateTopic;

    @Override
    public void sendProfileRequest(ProfileUpdateRequest profileUpdateRequest) throws JsonProcessingException {
        kafkaTemplate.send(profileUpdateTopic,objectMapper.writeValueAsString(profileUpdateRequest));
        log.info("send response to auth service for user :{}", profileUpdateRequest.getUserId());
    }
}
