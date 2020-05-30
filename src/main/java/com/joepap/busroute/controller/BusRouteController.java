package com.joepap.busroute.controller;

import com.joepap.busroute.common.RegularResponse;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import com.joepap.busroute.service.GBISBusRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/bus")
public class BusRouteController {

    private GBISBusRouteService gbisBusRouteService;

    public BusRouteController (GBISBusRouteService gbisBusRouteService) {
        this.gbisBusRouteService = gbisBusRouteService;
    }

    @GetMapping("/route/{areaId}")
    public ResponseEntity<RegularResponse> getBusRouteList (@PathVariable("areaId") Integer areaId) {
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(busRouteList)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/route/station/{routeId}")
    public ResponseEntity<RegularResponse> getBusRouteStationList (@PathVariable("routeId") String routeId) {
        List<BusRouteStationVO> busRouteList = gbisBusRouteService.getBusRouteStationListByRoute(routeId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(busRouteList)
                .build();

        return ResponseEntity.ok(response);
    }
}
