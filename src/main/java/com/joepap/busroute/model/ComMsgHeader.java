package com.joepap.busroute.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comMsgHeader")
public class ComMsgHeader {

    @XmlElement(name = "errMsg")
    public String errMsg;
    @XmlElement(name = "returnCode")
    public String returnCode;

}
