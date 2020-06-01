package com.joepap.busroute.model.gbis;


import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "response")
@Data
public class BusRouteLineResponse implements Serializable {

    private ComMsgHeader comMsgHeader;
    private MsgHeader msgHeader;
    private BusRouteLineBody msgBody;
}
