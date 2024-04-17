package com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.dto;

import com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.OilFromAggregatesWeights;
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
