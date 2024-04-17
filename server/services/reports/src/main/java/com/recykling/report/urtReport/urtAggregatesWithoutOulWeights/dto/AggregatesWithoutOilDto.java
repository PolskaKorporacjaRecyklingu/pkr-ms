package com.recykling.report.urtReport.urtAggregatesWithoutOulWeights.dto;

import com.recykling.report.urtReport.urtAggregatesWithoutOulWeights.AggregatesWithoutOilWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AggregatesWithoutOilDto {
    private List<Integer> aggregatesWithoutOilWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;

    public AggregatesWithoutOilDto(List<AggregatesWithoutOilWeights> aggregatesWithoutOil) {
       aggregatesWithoutOil
               .forEach(weight ->
                       aggregatesWithoutOilWeights.add(weight.getWeight()));
       this.count = aggregatesWithoutOilWeights.size();
       this.sumWeight = aggregatesWithoutOilWeights.stream().mapToInt(Integer::intValue).sum();
    }
}
