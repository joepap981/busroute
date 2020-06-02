package com.joepap.busroute.controller;

import com.joepap.busroute.common.RegularResponse;
import com.joepap.busroute.model.BusRouteLineVO;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import com.joepap.busroute.model.gbis.BusRouteInfoItem;
import com.joepap.busroute.model.request.GetRouteIdFromNameRequest;
import com.joepap.busroute.service.GBISBusRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/v1/bus")
public class BusRouteController {

    private GBISBusRouteService gbisBusRouteService;

    public BusRouteController (GBISBusRouteService gbisBusRouteService) {
        this.gbisBusRouteService = gbisBusRouteService;
    }

    /**
     * Search route by area
     * @param areaId
     * @return
     */
    @GetMapping("/route/list/{areaId}")
    public ResponseEntity<RegularResponse> getBusRouteList (@PathVariable("areaId") String areaId) {
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(busRouteList)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Search route details
     * @param routeId
     * @return
     */
    @GetMapping("/route/info/{routeId}")
    public ResponseEntity<RegularResponse> getRouteDetail (@PathVariable("routeId") String routeId) {
        BusRouteInfoItem busRouteInfo = gbisBusRouteService.getBusRouteInfo(routeId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(Collections.singletonList(busRouteInfo))
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Search Stations visited in route
     * @param routeId
     * @return
     */
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

    /**
     * Get route shape
     * @param routeId
     * @return
     */
    @GetMapping("/route/line/{routeId}")
    public ResponseEntity<RegularResponse> getBusRouteLineList (@PathVariable("routeId") String routeId) {
        List<BusRouteLineVO> busRouteLineList = gbisBusRouteService.getBusRouteLineByRouteId(routeId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(busRouteLineList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/route/routeId")
    public ResponseEntity<RegularResponse> getBusRouteIdFromName (@RequestBody GetRouteIdFromNameRequest request) {
        List<String> routeIdList = gbisBusRouteService.getRouteIdFromName(request.getAreaIdList(), request.getRouteNameList());
        RegularResponse response = RegularResponse.builder()
                .data(routeIdList)
                .build();

        return ResponseEntity.ok(response);
    }
}
