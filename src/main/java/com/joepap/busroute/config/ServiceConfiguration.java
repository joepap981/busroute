package com.joepap.busroute.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ServiceConfiguration {
    @Value("${gbis.base-url}")
    private String baseUrl;
    @Value("${gbis.service-key}")
    private String serviceKey;
}
