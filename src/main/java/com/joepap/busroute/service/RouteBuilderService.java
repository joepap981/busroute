package com.joepap.busroute.service;

import com.joepap.busroute.model.BusRouteStationVO;
import lombok.extern.slf4j.Slf4j;
import org.geojson.FeatureCollection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteBuilderService {
    private GBISBusRouteService gbisBusRouteService;
    private OpenRouteService openRouteService;

    public RouteBuilderService (GBISBusRouteService gbisBusRouteService, OpenRouteService openRouteService) {
        this.gbisBusRouteService = gbisBusRouteService;
        this.openRouteService = openRouteService;
    }

    public FeatureCollection buildBusRouteFeatureCollection (String routeId) {
        log.info("Building GSON for bus route {}.", routeId);
        List<BusRouteStationVO> busRouteStationList = gbisBusRouteService.getBusRouteStationListByRoute(routeId);

        List<Double[]> coordinates = busRouteStationList.stream()
                .map(station -> new Double[]{station.x, station.y})
                .collect(Collectors.toList());

        return openRouteService.getDirectionMultiCoordinates(coordinates);
    }
}
