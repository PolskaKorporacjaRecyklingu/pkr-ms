package com.recykling.report.urtReport.entities.urtOilFromAggregatesWeights;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_oil_from_aggregates_weights")
@NoArgsConstructor
@Getter
public class OilFromAggregatesWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "oil_from_aggregates_weights_id")
    private Long alCuRefrigeratorWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public OilFromAggregatesWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
