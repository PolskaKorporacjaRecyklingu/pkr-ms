package com.recykling.report.dto;

import com.recykling.report.entity.reports.AggregatesWithoutOilWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AggregatesWithoutOilDto {
    private List<Integer> aggregatesWithoutOilWeights = new ArrayList<>();
    private Integer aggregatesPackagingCount;
    private Integer aggregatesSumWeight;

    public AggregatesWithoutOilDto(List<AggregatesWithoutOilWeights> aggregatesWithoutOil) {
       aggregatesWithoutOil
               .forEach(weight ->
                       aggregatesWithoutOilWeights.add(weight.getWeight()));
       this.aggregatesPackagingCount = aggregatesWithoutOilWeights.size();
       this.aggregatesSumWeight = aggregatesWithoutOilWeights.stream().mapToInt(Integer::intValue).sum();
    }
}
