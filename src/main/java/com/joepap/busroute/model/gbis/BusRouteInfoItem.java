package com.joepap.busroute.model.gbis;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "busRouteInfoItem")
@Data
public class BusRouteInfoItem {
    private Long companyId;
    String companyName;
    String companyTel;
    Integer districtCd;
    String downFirstTime;
    String downLastTime;
    Integer endMobileNo;
    Long endStationId;
    String endStationName;
    Integer peekAlloc;
    String regionName;
    String routeId;
    String routeName;
    Integer routeTypeCd;
    String routeTypeName;
    Integer startMobileNo;
    Long startStationId;
    String startStationName;
    String upFirstTime;
    String upLastTime;
    Integer nPeekAlloc;
}
