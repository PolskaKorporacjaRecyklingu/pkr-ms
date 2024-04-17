package com.recykling.report.urtReport.manyToOne.urtRefrigeratorPowerCableWeights.dto;

import com.recykling.report.urtReport.manyToOne.urtRefrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class RefrigeratorPowerCableWeightsDto {
    private List<Integer> refrigeratorPowerCableWeights = new ArrayList<>();
    private Integer count;

    public RefrigeratorPowerCableWeightsDto(List<RefrigeratorPowerCableWeights> weights) {
        weights
               .forEach(weight ->
                       refrigeratorPowerCableWeights.add(weight.getWeight()));
       this.count = refrigeratorPowerCableWeights.size();
    }
}
