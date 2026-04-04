package com.example.demo.messagebroker.producer.impl;

import com.example.demo.messagebroker.model.TravellerProfileRequest;
import com.example.demo.messagebroker.producer.KafkaProducer;
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

    @Value("${kafka.topic.request.traveller-create}")
    private String travellerTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendProfileCreationRequest(TravellerProfileRequest travellerProfileRequest) throws JsonProcessingException {
        kafkaTemplate.send(travellerTopic, objectMapper.writeValueAsString(travellerProfileRequest));
    }
}
