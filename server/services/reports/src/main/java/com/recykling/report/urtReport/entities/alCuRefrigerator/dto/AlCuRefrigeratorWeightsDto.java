package com.recykling.report.urtReport.entities.alCuRefrigerator.dto;

import com.recykling.report.urtReport.entities.alCuRefrigerator.AlCuRefrigeratorWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AlCuRefrigeratorWeightsDto {
    private List<Integer> alCuRefrigeratorWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;
    private Integer alCuPackageIncomplete;
    public AlCuRefrigeratorWeightsDto(List<AlCuRefrigeratorWeights> weights, Integer alCuPackageIncomplete) {
        weights
               .forEach(weight ->
                       alCuRefrigeratorWeights.add(weight.getWeight()));
       this.count = alCuRefrigeratorWeights.size();
       this.sumWeight = alCuRefrigeratorWeights.stream().mapToInt(Integer::intValue).sum();
       this.alCuPackageIncomplete = alCuPackageIncomplete;
    }
}
