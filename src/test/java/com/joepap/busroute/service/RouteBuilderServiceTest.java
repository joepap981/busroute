package com.joepap.busroute.service;

import org.geojson.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteBuilderServiceTest {
    @Autowired
    RouteBuilderService routeBuilderService;

    @Test
    public void buildBusRouteLineFeatureCollection() {
        String routeId = "240000100";
        FeatureCollection features =  routeBuilderService.buildBusRouteLineFeatureCollection(routeId);
        features.getFeatures();
    }
}