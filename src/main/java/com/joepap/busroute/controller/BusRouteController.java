package com.joepap.busroute.controller;

import com.joepap.busroute.common.RegularResponse;
import com.joepap.busroute.model.BusRouteLineVO;
import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import com.joepap.busroute.model.gbis.BusRouteInfoItem;
import com.joepap.busroute.service.GBISBusRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/v1/bus")
public class BusRouteController {

    private GBISBusRouteService gbisBusRouteService;

    public BusRouteController (GBISBusRouteService gbisBusRouteService) {
        this.gbisBusRouteService = gbisBusRouteService;
    }

    /**
     * 지역별 노선목록 조회
     * @param areaId
     * @return
     */
    @GetMapping("/route/list/{areaId}")
    public ResponseEntity<RegularResponse> getBusRouteList (@PathVariable("areaId") Integer areaId) {
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(areaId);
        RegularResponse response = RegularResponse.builder()
                .responseCode(200)
                .message("success")
                .data(busRouteList)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * 노선상세 정보 조회
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
     * 노선경유정류소 조회
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
     * 노선형상 조회
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


}
