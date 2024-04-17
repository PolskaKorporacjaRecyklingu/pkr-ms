package com.recykling.report.urtReport.entities.urtAluminium.dto;

import com.recykling.report.urtReport.entities.urtOilFromAggregatesWeights.OilFromAggregatesWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AluminiumWeightsDto {
    private List<Integer> aluminiumWeights = new ArrayList<>();
    private Integer count;

    public AluminiumWeightsDto(List<OilFromAggregatesWeights> weights) {
        weights.forEach(weight -> aluminiumWeights.add(weight.getWeight()));
        this.count = aluminiumWeights.size();
    }
}
