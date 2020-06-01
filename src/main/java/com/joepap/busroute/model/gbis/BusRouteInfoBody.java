package com.joepap.busroute.model.gbis;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "msgBody")
@Data
public class BusRouteInfoBody {
    private BusRouteInfoItem busRouteInfoItem;
}
