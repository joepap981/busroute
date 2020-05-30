package com.joepap.busroute.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "msgBody")
public class BusRouteStationBody {

    @XmlElement(name = "busRouteStationList")
    public List<BusRouteStationVO> busRouteStationList;
}
