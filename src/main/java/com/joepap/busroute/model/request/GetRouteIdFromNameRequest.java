package com.joepap.busroute.model.request;

import lombok.Data;
import java.util.List;

@Data
public class GetRouteIdFromNameRequest {
    private List<String> areaIdList;
    private List<String> routeNameList;
}
