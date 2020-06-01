package com.joepap.busroute.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joepap.busroute.model.BusRouteLineVO;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import com.joepap.busroute.model.gbis.BusRouteInfoItem;
import lombok.extern.slf4j.Slf4j;
import org.geojson.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteBuilderService {
    private GBISBusRouteService gbisBusRouteService;
    ObjectMapper mapper;

    public RouteBuilderService (GBISBusRouteService gbisBusRouteService) {
        this.gbisBusRouteService = gbisBusRouteService;
        // TODO Threadpool management
        mapper = new ObjectMapper();
    }


    public FeatureCollection buildBusRouteLineFeatureCollection (String routeId) {
        log.info("Building GSON for bus route {}.", routeId);

        FeatureCollection featureCollection = new FeatureCollection();

        BusRouteInfoItem busRouteInfoItem = gbisBusRouteService.getBusRouteInfo(routeId);
        List<BusRouteLineVO> busRouteLineCoorList = gbisBusRouteService.getBusRouteLineByRouteId(routeId);

        Feature busRoute = new Feature();
        busRoute.setProperties(mapper.convertValue(busRouteInfoItem, Map.class));

        LineString lineString = new LineString();
        List<LngLatAlt> coordinates = busRouteLineCoorList.stream()
                .map(routeLine -> new LngLatAlt(routeLine.getX(), routeLine.getY()))
                .collect(Collectors.toList());
        lineString.setCoordinates(coordinates);
        busRoute.setGeometry(lineString);

        featureCollection.add(busRoute);

        return featureCollection;
    }


    public FeatureCollection buildBusRouteByAreaFeatureCollection (Integer areaId) {
        FeatureCollection features = new FeatureCollection();
        log.info("Building GSON for all bus routes in areaId : {}", areaId);
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);

        for (BusRouteVO busRoute : busRouteList) {
            log.info("Adding feature collection for busRoute {}({}).", busRoute.getRouteId(), busRoute.getRouteName());
            List<BusRouteLineVO> routeLinePointList = gbisBusRouteService.getBusRouteLineByRouteId(busRoute.getRouteId());

            FeatureCollection routeFeature = buildBusRouteLineFeatureCollection(busRoute.getRouteId());

        }
        return new FeatureCollection();
    }
}
