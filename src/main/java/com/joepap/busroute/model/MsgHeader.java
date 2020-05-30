package com.joepap.busroute.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "msgHeader")
public class MsgHeader {

    @XmlElement(name = "queryTime")
    public String queryTime;
    @XmlElement(name = "resultCode")
    public Integer resultCode;
    @XmlElement(name = "resultMessage")
    public String resultMessage;
}
