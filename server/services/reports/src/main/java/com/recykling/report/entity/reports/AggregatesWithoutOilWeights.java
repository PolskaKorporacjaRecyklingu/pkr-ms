package com.recykling.report.entity.reports;

import com.recykling.report.valueObjects.Weight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aggregates_without_oil_weights")
@NoArgsConstructor
@Getter
public class AggregatesWithoutOilWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregates_without_oil_weights_id")
    private Long aggregatesWithoutOilWeightsId;
    private Integer weight;
    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public AggregatesWithoutOilWeights(Weight weight, UrtReport urtReport) {
        this.weight = weight.weight();
        this.urtReport = urtReport;
    }
}
