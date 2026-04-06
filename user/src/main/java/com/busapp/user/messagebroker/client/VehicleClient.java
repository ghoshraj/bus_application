package com.busapp.user.messagebroker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VehicleClient {

    private final WebClient webClient;

    @Value("${serviceUrl.vehicle}")
    private String vehicleApi;

    public VehicleClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Integer getVehicleCount(Integer companyId) {
        return webClient.get()
                .uri(vehicleApi, companyId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
}
