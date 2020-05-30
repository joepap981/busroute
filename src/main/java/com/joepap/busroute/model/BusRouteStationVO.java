package com.joepap.busroute.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "busRouteStationList")
public class BusRouteStationVO {
    public String centerYn;
    public Integer districtCd;
    public Integer mobileNo;
    public String regionName;
    public Long stationId;
    public String stationName;
    public Double x;
    public Double y;
    public Integer stationSeq;
    public String turnYn;
}
