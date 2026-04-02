package com.example.demo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String kafkaServerAddress;

    @Value("${kafka.listener.concurrency}")
    private int concurrentConsumers;

    @Value("${kafka.consumer.max-attempts}")
    private long maxAttempts;

    @Value("${kafka.consumer.retry-interval}")
    private long retryInterval;

    public static final String PARTITION_ASSIGNMENT_STRATEGY = "org.apache.kafka.clients.consumer.CooperativeStickyAssignor";
    // ================= PRODUCER =================

    @Bean
    public Map<String, Object> kafkaConsumerConfiguration() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 120000);
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 10000);
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 172800000);
        properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 100);
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, PARTITION_ASSIGNMENT_STRATEGY);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaConsumerConfiguration(), new StringDeserializer(), new JsonDeserializer<>(String.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(KafkaAdmin kafkaAdmin) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setReplyTemplate(kafkaTemplate());
        factory.setConcurrency(concurrentConsumers);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    public Map<String, Object> kafkaProducerConfiguration() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        // Retry configuration
        FixedBackOff fixedBackOff = new FixedBackOff(retryInterval, maxAttempts - 1);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(fixedBackOff);
        // Optional: log each retry attempt
        errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
            System.out.println("Retry attempt: " + deliveryAttempt +
                    " for record: " + record.value());
        });

        return errorHandler;
    }
}
