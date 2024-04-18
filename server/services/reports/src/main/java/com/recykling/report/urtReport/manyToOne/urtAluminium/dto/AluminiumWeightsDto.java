package com.recykling.report.urtReport.manyToOne.urtAluminium.dto;

import com.recykling.report.urtReport.manyToOne.urtAluminium.AluminiumWeights;
import com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.OilFromAggregatesWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AluminiumWeightsDto {
    private List<Integer> aluminiumWeights = new ArrayList<>();
    private Integer count;

    public AluminiumWeightsDto(List<AluminiumWeights> weights) {
        weights.forEach(weight -> aluminiumWeights.add(weight.getWeight()));
        this.count = aluminiumWeights.size();
    }
}
