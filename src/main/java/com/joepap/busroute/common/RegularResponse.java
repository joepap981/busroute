package com.joepap.busroute.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class RegularResponse {
    private Integer responseCode = 200;
    private String message = "Success";
    private List<?> data;
}
