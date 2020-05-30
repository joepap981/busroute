package com.joepap.busroute.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "busRouteList")
public class BusRouteVO implements Serializable {
    @XmlElement(name = "districtCd")
    public Integer districtCd;
    @XmlElement(name = "regionName")
    public String regionName;
    @XmlElement(name = "routeId")
    public String routeId;
    @XmlElement(name = "routeName")
    public String routeName;
    @XmlElement(name = "routeTypeCd")
    public Integer routeTypeCd;
    @XmlElement(name = "routeTypeName")
    public String routeTypeName;
    
}
