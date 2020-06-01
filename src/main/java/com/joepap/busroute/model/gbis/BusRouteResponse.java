package com.joepap.busroute.model.gbis;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@Data
public class BusRouteResponse {
    private ComMsgHeader comMsgHeader;
    private MsgHeader msgHeader;
    private BusRouteBody msgBody;
}
