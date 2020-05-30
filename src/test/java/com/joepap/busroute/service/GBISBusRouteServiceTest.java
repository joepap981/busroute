package com.joepap.busroute.service;

import com.joepap.busroute.model.XMLResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GBISBusRouteServiceTest {
    @Autowired
    GBISBusRouteService gbisBusRouteService;

    @Test
    public void getBusRouteListByArea() {
        XMLResponse response = gbisBusRouteService.getBusRouteListByArea(19);
        System.out.println(response);
    }
}