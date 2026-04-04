package com.example.demo.scheduler;

import com.example.demo.entity.User;
import com.example.demo.enums.ProfileStatus;
import com.example.demo.messagebroker.model.TravellerProfileRequest;
import com.example.demo.messagebroker.producer.KafkaProducer;
import com.example.demo.service.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileRetryScheduler {

    private final UserPersistenceService userPersistenceService;
    private final KafkaProducer kafkaProducer;

//    @Scheduled(fixedDelay = 60000)
    @Scheduled(fixedDelay = 86400000)
    public void retryPendingProfiles() {

        log.info("Starting retry for pending profiles...");
        List<User> pendingUsers = userPersistenceService.findByStatus(ProfileStatus.PENDING);
        if (pendingUsers.isEmpty()) {
            log.info("No pending users found");
            return;
        }

        for (User user : pendingUsers) {
            try {
                log.info("Retrying profile creation for user: {}", user.getId());

                TravellerProfileRequest event = TravellerProfileRequest.builder()
                        .userId(user.getId())
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .gender(user.getGender())
                        .build();
                kafkaProducer.sendProfileCreationRequest(event);

            } catch (Exception e) {
                log.error("Failed to retry for user: {}", user.getId(), e);
            }
        }
    }
}
