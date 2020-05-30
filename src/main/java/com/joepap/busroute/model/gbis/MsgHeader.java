package com.joepap.busroute.model.gbis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "msgHeader")
public class MsgHeader {

    public String queryTime;
    public Integer resultCode;
    public String resultMessage;
}
