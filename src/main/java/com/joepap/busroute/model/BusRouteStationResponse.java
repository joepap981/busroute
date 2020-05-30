package com.joepap.busroute.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "response")
public class BusRouteStationResponse implements Serializable {

    public ComMsgHeader comMsgHeader;
    public MsgHeader msgHeader;
    @XmlElement(name = "msgBody")
    public BusRouteStationBody msgBody;
}
