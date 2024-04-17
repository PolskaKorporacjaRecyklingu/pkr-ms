package com.recykling.report.urtReport.entities.urtPsAbsRefrigeratorWeights.dto;

import com.recykling.report.urtReport.entities.urtPsAbsRefrigeratorWeights.PsAbsRefrigeratorWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class PsAbsRefrigeratorWeightsDto {
    private List<Integer> psAbsRefrigeratorWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;
    private Integer psAbsRefrigeratorIncompleteWeight;
    public PsAbsRefrigeratorWeightsDto(List<PsAbsRefrigeratorWeights> weights, Integer psAbsRefrigeratorIncompleteWeight) {
        weights
               .forEach(weight ->
                       psAbsRefrigeratorWeights.add(weight.getWeight()));
       this.count = psAbsRefrigeratorWeights.size();
       this.sumWeight = psAbsRefrigeratorWeights.stream().mapToInt(Integer::intValue).sum();
       this.psAbsRefrigeratorIncompleteWeight = psAbsRefrigeratorIncompleteWeight;
    }
}
