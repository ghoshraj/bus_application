package com.busapp.user.messagebroker.consumer.impl;

import com.busapp.user.config.ObjectMapperHelper;
import com.busapp.user.entity.TravellerProfiles;
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
    private final ObjectMapperHelper objectMapperHelper;

    @Override
    @KafkaListener(topics = "${kafka.topic.request.traveller-create}", groupId = "${kafka.group.traveller-create}")
    public void listenTravellerResponse(String msg) throws JsonProcessingException {
        TravellerProfiles profile = objectMapperHelper.fromJson(msg, TravellerProfiles.class);
        travellerProfileService.createProfile(profile);
    }
}
