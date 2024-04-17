package com.recykling.report.urtReport.entities.urtAggregatesWithOilWeights;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_aggregates_with_oil_weights")
@NoArgsConstructor
@Getter
public class AggregatesWithOilWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregates_with_oil_weights_id")
    private Long aggregatesWithOilWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public AggregatesWithOilWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
