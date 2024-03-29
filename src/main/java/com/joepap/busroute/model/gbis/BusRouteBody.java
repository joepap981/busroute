package com.joepap.busroute.model.gbis;

import com.joepap.busroute.model.BusRouteVO;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "msgBody")
@Data
public class BusRouteBody {
    private List<BusRouteVO> busRouteList;
}
