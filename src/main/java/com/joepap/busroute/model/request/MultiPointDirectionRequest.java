package com.joepap.busroute.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MultiPointDirectionRequest {
    private List<Double[]> coordinates;
}
