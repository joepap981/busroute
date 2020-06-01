package com.joepap.busroute.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "busRouteStationList")
@Data
public class BusRouteStationVO {
    private String centerYn;
    private Integer districtCd;
    private Integer mobileNo;
    private String regionName;
    private Long stationId;
    private String stationName;
    private Double x;
    private Double y;
    private Integer stationSeq;
    private String turnYn;
}
