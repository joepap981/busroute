package com.joepap.busroute.model.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateGSONByRouteNameRequest {
    List<String> routeNameList;
    List<String> areaIdList;
}
