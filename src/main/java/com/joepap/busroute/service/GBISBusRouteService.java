package com.joepap.busroute.service;

import com.joepap.busroute.config.ServiceConfiguration;
import com.joepap.busroute.model.XMLResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.URI;
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

    public XMLResponse getBusRouteListByArea (int areaId) {
        XMLResponse xmlResponse = new XMLResponse();
        String operationName = "/area";

        URI uri = URI.create(String.format("%s%s%s?serviceKey=%s&areaId=%d"
                , serviceConf.getBaseUrl()
                , serviceName
                , operationName
                , serviceConf.getServiceKey()
                , areaId
        ));

        try {
            String xmlResponseString = webClient.get().uri(uri).retrieve().bodyToFlux(String.class).blockFirst();
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (XMLResponse) jaxbUnmarshaller.unmarshal(new StringReader(Objects.requireNonNull(xmlResponseString)));
        } catch (JAXBException e) {
            log.error("Failed to create jaxbMarshaller.");
        } catch (NullPointerException e) {{
            log.error("Null response for client request : " + operationName);
        }}

        return xmlResponse;
    }
}
