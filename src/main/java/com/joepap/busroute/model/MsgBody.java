package com.joepap.busroute.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "msgBody")
public class MsgBody {

    @XmlElement(name = "busRouteList")
    public List<BusRouteVO> busRouteVOList;
}
