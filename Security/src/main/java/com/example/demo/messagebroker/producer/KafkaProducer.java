package com.example.demo.messagebroker.producer;

import com.example.demo.messagebroker.model.TravellerProfileRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaProducer {

    void sendProfileCreationRequest(TravellerProfileRequest travellerProfileRequest) throws JsonProcessingException;
}
