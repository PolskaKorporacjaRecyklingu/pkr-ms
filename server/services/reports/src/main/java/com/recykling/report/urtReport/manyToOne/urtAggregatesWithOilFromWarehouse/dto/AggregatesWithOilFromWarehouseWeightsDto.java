package com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilFromWarehouse.dto;

import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilFromWarehouse.AggregatesWithOilFromWarehouseWeights;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class AggregatesWithOilFromWarehouseWeightsDto {
    private List<Integer> AggregatesWithOilFromWarehouseWeights = new ArrayList<>();
    private Integer count;
    private Integer sumWeight;

    public AggregatesWithOilFromWarehouseWeightsDto(List<AggregatesWithOilFromWarehouseWeights> weights) {
        weights.forEach(weight -> AggregatesWithOilFromWarehouseWeights.add(weight.getWeight()));
        this.count = AggregatesWithOilFromWarehouseWeights.size();
        this.sumWeight = AggregatesWithOilFromWarehouseWeights.stream().mapToInt(Integer::intValue).sum();
    }
}
