package com.joepap.busroute.service;

import com.joepap.busroute.config.ServiceConfiguration;
import com.joepap.busroute.model.BusRouteLineVO;
import com.joepap.busroute.model.gbis.*;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;


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
        this.webClient = WebClient.create(serviceConf.getGbisBaseUrl());
    }

    public List<BusRouteVO> getBusRouteListByArea (int areaId) {
        log.info("Requesting busRouteList for area {}.", areaId);
        BusRouteResponse xmlResponse = new BusRouteResponse();
        String operationName = "/area";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("areaId",Integer.toString(areaId)));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
            if (xmlResponse == null || xmlResponse.getMsgBody() == null) {
                log.error("No content found for areaId : {}", areaId);
                throw new HttpServerErrorException(HttpStatus.NO_CONTENT);
            }
        }
        catch (JAXBException e) {
            log.error("Failed to create jaxbMarshaller.");
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException e) {
            log.error("Null response for client request : {}", operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (URISyntaxException e) {
            log.error("Failed building URI for : {}.", operationName);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return xmlResponse.getMsgBody().getBusRouteList();
    }

    public BusRouteInfoItem getBusRouteInfo (String routeId) {
        log.info("Requesting bus route info for routeId : {}", routeId);
        BusRouteInfoResponse xmlResponse = new BusRouteInfoResponse();
        String operationName = "/info";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("routeId", routeId));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteInfoResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteInfoResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
            if (xmlResponse == null || xmlResponse.getMsgBody() == null) {
                log.info("No BusRouteInfo found for route {}", routeId);
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
            }
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

        BusRouteInfoItem busRouteInfoItem = xmlResponse.getMsgBody().getBusRouteInfoItem();
        log.info("Received {}", busRouteInfoItem);
        return busRouteInfoItem;
    }

    public List<BusRouteStationVO> getBusRouteStationListByRoute (String routeId) {
        log.info("Requesting busRouteStationList for route {}.", routeId);
        BusRouteStationResponse xmlResponse = new BusRouteStationResponse();
        String operationName = "/station";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("routeId", routeId));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteStationResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteStationResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
            if (xmlResponse == null || xmlResponse.getMsgBody() == null) {
                log.info("No BusRouteStation found for route {}", routeId);
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
            }
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

        return xmlResponse.getMsgBody().getBusRouteStationList();
    }

    public List<BusRouteLineVO> getBusRouteLineByRouteId (String routeId) {
        log.info("Requesting busRouteLineList for route {}.", routeId);
        BusRouteLineResponse xmlResponse = new BusRouteLineResponse();
        String operationName = "/line";

        try {
            URI uri = uriBuilder(operationName, Collections.singletonMap("routeId", routeId));
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(BusRouteLineResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (BusRouteLineResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
            if (xmlResponse == null || xmlResponse.getMsgBody() == null) {
                log.info("No BusRouteLine found for route {}", routeId);
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
            }
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

        return xmlResponse.getMsgBody().getBusRouteLineList();
    }

    private URI uriBuilder (String operationName, Map<String, String> queryParam) throws URISyntaxException {
        StringBuilder sb = new StringBuilder()
                .append(serviceConf.getGbisBaseUrl())
                .append(serviceName)
                .append(operationName)
                .append("?serviceKey").append("=").append(serviceConf.getGbisServiceKey());


        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        return new URI(sb.toString());
    }
}
