package com.joepap.busroute.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "response")
public class XMLResponse implements Serializable {

    @XmlElement(name = "comMsgHeader")
    public ComMsgHeader comMsgHeader;

    @XmlElement(name = "msgHeader")
    public MsgHeader msgHeader;

    @XmlElement(name = "msgBody")
    public MsgBody msgBody;


    public XMLResponse () {

    }

    public XMLResponse(ComMsgHeader comMsgHeader, MsgHeader msgHeader, MsgBody msgBody) {
        this.comMsgHeader = comMsgHeader;
        this.msgHeader = msgHeader;
        this.msgBody = msgBody;
    }
}
