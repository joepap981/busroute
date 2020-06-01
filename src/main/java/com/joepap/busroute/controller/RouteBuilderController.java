package com.joepap.busroute.controller;

import com.joepap.busroute.service.RouteBuilderService;
import org.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/routegson/")
public class RouteBuilderController {

    private RouteBuilderService routeBuilderService;

    public RouteBuilderController (RouteBuilderService routeBuilderService) {
        this.routeBuilderService = routeBuilderService;
    }

    /**
     * 畴急 GSON 积己
     * @param routeId
     * @return
     */
    @GetMapping("/route/{routeId}")
    public ResponseEntity<FeatureCollection> getBusRouteGSONByRoute (@PathVariable("routeId") String routeId) {
        FeatureCollection featureCollection = routeBuilderService.buildBusRouteLineFeatureCollection(routeId);
        return ResponseEntity.ok(featureCollection);
    }

    /**
     * 瘤开 畴急 GSON 积己
     * @param areaId
     * @return
     */
    @GetMapping("/area/{areaId}")
    public ResponseEntity<FeatureCollection> getBusRouteGSONByArea (@PathVariable("areaId") Integer areaId) {
        FeatureCollection featureCollection = routeBuilderService.buildBusRouteByAreaFeatureCollection(areaId);
        return ResponseEntity.ok(featureCollection);
    }
}
