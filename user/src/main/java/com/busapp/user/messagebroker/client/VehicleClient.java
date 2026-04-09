package com.busapp.user.messagebroker.client;

import com.busapp.user.model.VehicleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class VehicleClient {

    private final WebClient webClient;

    @Value("${serviceUrl.vehicle}")
    private String vehicleApi;

    public VehicleClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<VehicleResponse> getVehicle(Integer companyId, String token) {
        return webClient.get()
                .uri(vehicleApi, companyId)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(VehicleResponse.class)
                .collectList()
                .block();
    }
}
