package com.busapp.user.messagebroker.producer;

import com.busapp.user.messagebroker.model.ProfileUpdateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaProducer {

    void sendProfileRequest(ProfileUpdateRequest profileUpdateRequest) throws JsonProcessingException;
}
