package com.joepap.busroute.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "busRouteLineList")
@Data
public class BusRouteLineVO {
    private Integer lineSeq;
    private Double x;
    private Double y;
}
