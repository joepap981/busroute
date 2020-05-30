package com.joepap.busroute.service;

import com.joepap.busroute.config.ServiceConfiguration;
import com.joepap.busroute.model.BusRouteResponse;
import com.joepap.busroute.model.BusRouteStationResponse;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;


import javax.print.URIException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class GBISBusRouteService {
    private WebClient webClient;
    private ServiceConfiguration serviceConf;

    private final String serviceName = "/busrouteservice";


    public GBISBusRouteService (ServiceConfiguration serviceConfiguration) {
        this.serviceConf = serviceConfiguration;
        this.webClient = WebClient.create(serviceConf.getBaseUrl());
    }

    public List<BusRouteVO> getBusRouteListByArea (int areaId) {
        BusRouteResponse xmlResponse = new BusRouteResponse();
        String operationName = "/area";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("areaId",Integer.toString(areaId)));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
        }
        catch (JAXBException e) {
            log.error("Failed to create jaxbMarshaller.");
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException e) {{
            log.error("Null response for client request : {}", operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }} catch (URISyntaxException e) {
            log.error("Failed building URI for : {}.", operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return xmlResponse.busRouteBody.busRouteVOList;
    }

    public List<BusRouteStationVO> getBusRouteStationListByRoute (String routeId) {
        BusRouteStationResponse xmlResponse = new BusRouteStationResponse();
        String operationName = "/station";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("routeId", routeId));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteStationResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteStationResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
        } catch (JAXBException e) {
            log.error("Failed to create jaxbMarshaller.");
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException e) {{
            log.error("Null response for client request : " + operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }} catch (URISyntaxException e) {
            log.error("Failed building URI for : {}.", operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return xmlResponse.msgBody.busRouteStationList;
    }

    private URI uriBuilder (String operationName, Map<String, String> queryParam) throws URISyntaxException {
        StringBuilder sb = new StringBuilder()
                .append(serviceConf.getBaseUrl())
                .append(serviceName)
                .append(operationName)
                .append("?serviceKey").append("=").append(serviceConf.getServiceKey());


        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        return new URI(sb.toString());
    }
}
