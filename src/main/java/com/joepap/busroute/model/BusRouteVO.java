package com.joepap.busroute.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "busRouteList")
public class BusRouteVO implements Serializable {

    public Integer districtCd;
    public String regionName;
    public String routeId;
    public String routeName;
    public Integer routeTypeCd;
    public String routeTypeName;
    
}
