package com.joepap.busroute.service;

import com.joepap.busroute.model.BusRouteStationVO;
import com.joepap.busroute.model.BusRouteVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GBISBusRouteServiceTest {
    @Autowired
    GBISBusRouteService gbisBusRouteService;

    @Test
    public void getBusRouteListByArea() {
        List<BusRouteVO> busRouteList = gbisBusRouteService.getBusRouteListByArea("19");
        assertEquals(141, busRouteList.size());
    }

    @Test
    public void getBusRouteStationListByRoute() {
        List<BusRouteStationVO> busRouteStationList = gbisBusRouteService.getBusRouteStationListByRoute("240000114");
        assertEquals(28, busRouteStationList.size());
    }

    @Test
    public void getRouteIdFromName() {

        List<String> areaIdList = new ArrayList<>();
        List<String> routeNameList = new ArrayList<>();


        areaIdList.addAll(Arrays.asList(new String[]{"05", "19", "26", "16"}));
        routeNameList.add("1-6");
        routeNameList.add("10");
        routeNameList.add("110");
        routeNameList.add("111");
        routeNameList.add("120");
        routeNameList.add("130");

        List<String> routeIdList = gbisBusRouteService.getRouteIdFromName(areaIdList, routeNameList);
        System.out.println(routeIdList);


    }
}