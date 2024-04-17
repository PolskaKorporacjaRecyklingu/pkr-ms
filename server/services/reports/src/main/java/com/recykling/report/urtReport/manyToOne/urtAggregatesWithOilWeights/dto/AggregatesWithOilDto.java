package com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.dto;

import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.AggregatesWithOilWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AggregatesWithOilDto {
    private List<Integer> aggregatesWithOilWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;

    public AggregatesWithOilDto(List<AggregatesWithOilWeights> weights) {
        weights
               .forEach(weight ->
                       aggregatesWithOilWeights.add(weight.getWeight()));
       this.count = aggregatesWithOilWeights.size();
       this.sumWeight = aggregatesWithOilWeights.stream().mapToInt(Integer::intValue).sum();
    }
}
