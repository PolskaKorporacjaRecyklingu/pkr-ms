package com.recykling.report.urtReport.entities.urtAlCuRefrigerator;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_al_cu_refrigerator_weights")
@NoArgsConstructor
@Getter
public class AlCuRefrigeratorWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregates_without_oil_weights_id")
    private Long alCuRefrigeratorWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public AlCuRefrigeratorWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
