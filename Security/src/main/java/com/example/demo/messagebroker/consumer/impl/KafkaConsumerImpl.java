package com.example.demo.messagebroker.consumer.impl;

import com.example.demo.config.ObjectMapperHelper;
import com.example.demo.messagebroker.consumer.KafkaConsumer;
import com.example.demo.messagebroker.model.ProfileStatusUpdateRequest;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

    private final UserService userService;
    private final ObjectMapperHelper objectMapperHelper;

    @Override
    @KafkaListener(topics = "${kafka.topic.response.traveller-update}", groupId = "${kafka.group.traveller-update}")
    public void listenProfileUpdateResponse(String msg) throws JsonProcessingException {
        ProfileStatusUpdateRequest profileStatusUpdateRequest = objectMapperHelper.fromJson(msg, ProfileStatusUpdateRequest.class);
        userService.updateProfileStatus(profileStatusUpdateRequest.getUserId());
    }
}
