package com.joepap.busroute.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joepap.busroute.model.BusRouteLineVO;
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


    public Feature buildBusRouteLineFeatureCollection (String routeId) {
        log.info("Building GSON for bus route {}.", routeId);

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

        log.info("Finished building GSON for bus route {}.", routeId);

        return busRoute;
    }


    public FeatureCollection buildBusRouteByAreaFeatureCollection (String areaId) {
        FeatureCollection featureCollection = new FeatureCollection();

        log.info("Building GSON for all bus routes in areaId : {}", areaId);
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);

        for (BusRouteVO busRoute : busRouteList) {
            log.info("Adding feature for busRoute {}({}).", busRoute.getRouteId(), busRoute.getRouteName());
            featureCollection.add(buildBusRouteLineFeatureCollection(busRoute.getRouteId()));
            log.info("Added feature.");
        }

        return featureCollection;
    }

    public FeatureCollection buildBusRouteByRouteName (List<String> areaIdList, List<String> routeNameList) {
        FeatureCollection featureCollection = new FeatureCollection();

        List<String> routeIdList = gbisBusRouteService.getRouteIdFromName(areaIdList, routeNameList);

        for (String routeId : routeIdList) {
            Feature feature = buildBusRouteLineFeatureCollection(routeId);
            featureCollection.add(feature);
        }

        return featureCollection;
    }
}
