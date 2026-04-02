package com.example.demo.messagebroker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaConsumer {

    void listenTravellerResponse(String msg) throws JsonProcessingException;
}
