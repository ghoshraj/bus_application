package com.busapp.user.messagebroker.consumer.impl;

import com.busapp.user.messagebroker.consumer.KafkaConsumer;
import com.busapp.user.service.TravellerProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

    private final TravellerProfileService travellerProfileService;

    @Override
    @KafkaListener(topics = "${kafka.topic.traveller}", groupId = "${kafka.group.traveller}")
    public void listenTravellerResponse(String msg) throws JsonProcessingException {
        travellerProfileService.createProfile(msg);
    }
}
