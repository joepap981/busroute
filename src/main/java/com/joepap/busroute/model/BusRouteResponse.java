package com.joepap.busroute.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class BusRouteResponse {
    public ComMsgHeader comMsgHeader;
    public MsgHeader msgHeader;
    @XmlElement(name = "msgBody")
    public BusRouteBody busRouteBody;
}
