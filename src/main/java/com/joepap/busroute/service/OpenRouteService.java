package com.joepap.busroute.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class OpenRouteService {

    private WebClient webClient;

    public OpenRouteService (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

}
