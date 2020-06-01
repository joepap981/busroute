package com.joepap.busroute.model.gbis;


import com.joepap.busroute.model.BusRouteLineVO;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "msgBody")
@Data
public class BusRouteLineBody {
    private List<BusRouteLineVO> busRouteLineList;
}
