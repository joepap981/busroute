package com.joepap.busroute.model.gbis;

import com.joepap.busroute.model.BusRouteVO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "msgBody")
public class BusRouteBody {

    @XmlElement(name = "busRouteList")
    public List<BusRouteVO> busRouteVOList;
}
