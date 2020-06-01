package com.joepap.busroute.service;

import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
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
                .map(station -> new Double[]{station.getX(), station.getY()})
                .collect(Collectors.toList());

        return openRouteService.getDirectionMultiCoordinates(coordinates);
    }


    public FeatureCollection buildBusRouteByAreaFeatureCollection (Integer areaId) {
        FeatureCollection features = new FeatureCollection();
        log.info("Building GSON for all bus routes in areaId : {}", areaId);
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);

//        for (BusRouteVO busRoute : busRouteList) {
//            log.info("Adding feature collection for busRoute ");
//
//        }
        return new FeatureCollection();
    }
}
