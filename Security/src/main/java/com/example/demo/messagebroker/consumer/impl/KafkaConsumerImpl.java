package com.example.demo.messagebroker.consumer.impl;

import com.example.demo.messagebroker.consumer.KafkaConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

    @Override
    public void listenTravellerResponse(String msg) throws JsonProcessingException {

    }
}
