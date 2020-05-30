package com.joepap.busroute.model.gbis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class BusRouteResponse {
    public ComMsgHeader comMsgHeader;
    public MsgHeader msgHeader;
    @XmlElement(name = "msgBody")
    public BusRouteBody busRouteBody;
}
