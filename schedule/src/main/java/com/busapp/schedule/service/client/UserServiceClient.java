package com.busapp.schedule.service.client;

import com.busapp.schedule.exception.GlobalExceptionEnums;
import com.busapp.schedule.exception.UserServiceException;
import com.busapp.schedule.model.OperatorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${services.user-service.url:http://user_service}")
    private String userServiceUrl;

    public Mono<OperatorResponse> getOperatorByVehicleId(Integer vehicleId) {
        return webClientBuilder.build()
                .get()
                .uri(userServiceUrl + "/companies/drivers/vehicle/{vehicleId}", vehicleId)
                .retrieve()
                .bodyToMono(OperatorResponse.class)
                .onErrorMap(throwable -> new UserServiceException(
                        GlobalExceptionEnums.USER_SERVICE_ERROR, throwable.getMessage()));
    }
}
