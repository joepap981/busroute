package com.joepap.busroute.service;

import com.joepap.busroute.config.ServiceConfiguration;
import com.joepap.busroute.model.openroute.CoordinatesVO;
import com.joepap.busroute.model.request.MultiPointDirectionRequest;
import lombok.extern.slf4j.Slf4j;
import org.geojson.FeatureCollection;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@Deprecated
public class OpenRouteService {

    private WebClient webClient;
    private ServiceConfiguration serviceConf;

    public OpenRouteService (WebClient.Builder webClientBuilder, ServiceConfiguration serviceConfiguration) {
        this.serviceConf = serviceConfiguration;
        this.webClient = webClientBuilder.baseUrl(serviceConf.getOpenRouteBaseUrl()).build();
    }

    public FeatureCollection getDirectionMultiCoordinates (List<Double[]> coordinateList) {
        MultiPointDirectionRequest request = new MultiPointDirectionRequest(coordinateList);

        return this.webClient.post().uri("/v2/directions/driving-car/geojson")
                .header("Authorization", serviceConf.getOpenRouteApiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(Mono.just(request), MultiPointDirectionRequest.class))
                .retrieve().bodyToFlux(FeatureCollection.class).blockFirst();
    }
}
