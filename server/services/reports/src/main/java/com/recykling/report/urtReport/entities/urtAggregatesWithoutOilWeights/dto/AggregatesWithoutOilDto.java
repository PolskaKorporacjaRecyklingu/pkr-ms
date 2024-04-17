package com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.dto;

import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.AggregatesWithoutOilWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AggregatesWithoutOilDto {
    private List<Integer> aggregatesWithoutOilWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;

    public AggregatesWithoutOilDto(List<AggregatesWithoutOilWeights> weights) {
        weights
               .forEach(weight ->
                       aggregatesWithoutOilWeights.add(weight.getWeight()));
       this.count = aggregatesWithoutOilWeights.size();
       this.sumWeight = aggregatesWithoutOilWeights.stream().mapToInt(Integer::intValue).sum();
    }
}
