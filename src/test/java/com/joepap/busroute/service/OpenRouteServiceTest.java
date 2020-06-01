package com.joepap.busroute.service;

import com.joepap.busroute.model.BusRouteStationVO;
import org.geojson.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenRouteServiceTest {
    @Autowired
    OpenRouteService openRouteService;

    @Autowired
    GBISBusRouteService gbisBusRouteService;

    @Test
    public void getDirectionMultiCoordinates() {
        List<BusRouteStationVO> busRouteStationList = gbisBusRouteService.getBusRouteStationListByRoute("240000114");

        List<Double[]> coordinates = busRouteStationList.stream()
                .map(station -> new Double[]{station.getX(), station.getY()})
                .collect(Collectors.toList());

        FeatureCollection featureCollection = openRouteService.getDirectionMultiCoordinates(coordinates);
        System.out.println(coordinates);
    }
}