package com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilFromWarehouse;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_aggregates_with_oil_from_warehouse_weights")
@NoArgsConstructor
@Getter
public class AggregatesWithOilFromWarehouseWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregates_with_oil_from_warehouse_weights_id")
    private Long aggregatesWithOilFromWarehouseWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public AggregatesWithOilFromWarehouseWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
