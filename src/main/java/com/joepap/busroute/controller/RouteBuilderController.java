package com.joepap.busroute.controller;

import com.joepap.busroute.service.RouteBuilderService;
import org.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/builder")
public class RouteBuilderController {

    private RouteBuilderService routeBuilderService;

    public RouteBuilderController (RouteBuilderService routeBuilderService) {
        this.routeBuilderService = routeBuilderService;
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<FeatureCollection> getBusRouteGson (@PathVariable("routeId") String routeId) {
        FeatureCollection featureCollection = routeBuilderService.buildBusRouteFeatureCollection(routeId);
        return ResponseEntity.ok(featureCollection);
    }
}
