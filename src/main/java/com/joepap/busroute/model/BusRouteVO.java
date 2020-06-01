package com.joepap.busroute.model;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "busRouteList")
@Data
public class BusRouteVO implements Serializable {

    private Integer districtCd;
    private String regionName;
    private String routeId;
    private String routeName;
    private Integer routeTypeCd;
    private String routeTypeName;
    
}
