package com.busapp.seatinventory.service.client;

import com.busapp.seatinventory.exception.GlobalExceptionEnums;
import com.busapp.seatinventory.exception.UserServiceException;
import com.busapp.seatinventory.model.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ScheduleServiceClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${services.schedule-service.url:http://schedule_service}")
    private String scheduleServiceUrl;

    public Mono<ScheduleResponse> getScheduleById(Integer scheduleId) {
        return webClientBuilder.build()
                .get()
                .uri(scheduleServiceUrl + "/schedules/{id}", scheduleId)
                .retrieve()
                .bodyToMono(ScheduleResponse.class)
                .onErrorMap(throwable -> new UserServiceException(
                        GlobalExceptionEnums.USER_SERVICE_ERROR, throwable.getMessage()));
    }
}
