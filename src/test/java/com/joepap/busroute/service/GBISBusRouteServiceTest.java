package com.joepap.busroute.service;

import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GBISBusRouteServiceTest {
    @Autowired
    GBISBusRouteService gbisBusRouteService;

    @Test
    public void getBusRouteListByArea() {
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea(19);
        assertEquals(141, busRouteList.size());
    }

    @Test
    public void getBusRouteStationListByRoute() {
        List<BusRouteStationVO> busRouteStationList = gbisBusRouteService.getBusRouteStationListByRoute("240000114");
        assertEquals(28, busRouteStationList.size());
    }
}