package com.joepap.busroute.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ServiceConfiguration {
    /** GBIS */
    @Value("${hosts.gbis.base-url}")
    private String gbisBaseUrl;
    @Value("${hosts.gbis.service-key}")
    private String gbisServiceKey;

    /** OpenRoute **/
    @Value("${hosts.open-route.base-url}")
    private String openRouteBaseUrl;
    @Value("${hosts.open-route.api-key}")
    private String openRouteApiKey;

}
