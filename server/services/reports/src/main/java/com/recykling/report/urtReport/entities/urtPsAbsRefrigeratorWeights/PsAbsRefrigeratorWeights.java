package com.recykling.report.urtReport.entities.urtPsAbsRefrigeratorWeights;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_ps_abs_refrigerator_weights")
@NoArgsConstructor
@Getter
public class PsAbsRefrigeratorWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "oil_from_aggregates_weights_id")
    private Long psAbsRefrigeratorWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public PsAbsRefrigeratorWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
