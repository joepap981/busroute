package com.joepap.busroute.model.gbis;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comMsgHeader")
public class ComMsgHeader {

    public String errMsg;
    public String returnCode;

}
