package com.busapp.user.messagebroker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaConsumer {

    void listenTravellerResponse(String msg) throws JsonProcessingException;
}
