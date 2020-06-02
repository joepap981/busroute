package com.joepap.busroute.controller;

import com.joepap.busroute.model.request.CreateGSONByRouteNameRequest;
import com.joepap.busroute.service.RouteBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/v1/routegson/")
public class RouteBuilderController {

    private RouteBuilderService routeBuilderService;

    public RouteBuilderController (RouteBuilderService routeBuilderService) {
        this.routeBuilderService = routeBuilderService;
    }

    /**
     * Get Route GSON
     * @param routeId
     * @return
     */
    @GetMapping("/route/{routeId}")
    public ResponseEntity<Feature> getBusRouteGSONByRoute (@PathVariable("routeId") String routeId) {
        Feature feature = routeBuilderService.buildBusRouteLineFeatureCollection(routeId);
        return ResponseEntity.ok(feature);
    }

    /**
     * Get AREA route GSON
     * @param areaId
     * @return
     */
    @GetMapping("/area/{areaId}")
    public ResponseEntity<FeatureCollection> getBusRouteGSONByArea (@PathVariable("areaId") String areaId) {
        log.info("getBusRouteGSONByArea : Started for areaId : {}", areaId);

        FeatureCollection featureCollection = routeBuilderService.buildBusRouteByAreaFeatureCollection(areaId);

        log.info("getBusRouteGSONByArea : Successfully built GSON for areaId : {}", areaId);
        return ResponseEntity.ok(featureCollection);
    }

    @PostMapping("/route/name")
    public ResponseEntity<FeatureCollection> getBusRouteGSONByRouteName (@RequestBody CreateGSONByRouteNameRequest request) {
        log.info("getBusRouteGSONByRouteName");
        FeatureCollection features = routeBuilderService.buildBusRouteByRouteName(request.getAreaIdList(), request.getRouteNameList());
        log.info("getBusRouteGSONByRouteName : Successfully built GSON for routeNames");
        return ResponseEntity.ok(features);
    }
}
