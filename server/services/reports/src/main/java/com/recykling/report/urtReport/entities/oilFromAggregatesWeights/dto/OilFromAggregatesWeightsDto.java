package com.recykling.report.urtReport.entities.oilFromAggregatesWeights.dto;

import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.OilFromAggregatesWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class OilFromAggregatesWeightsDto {
    private List<Integer> oilFromAggregatesWeights = new ArrayList<>();
    private Integer count;

    public OilFromAggregatesWeightsDto(List<OilFromAggregatesWeights> weights) {
        weights
               .forEach(weight ->
                       oilFromAggregatesWeights.add(weight.getWeight()));
       this.count = oilFromAggregatesWeights.size();
    }
}
