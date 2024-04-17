package com.recykling.report.urtReport.manyToOne.urtRefrigeratorPowerCableWeights;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_refrigerator_power_cable_weights")
@NoArgsConstructor
@Getter
public class RefrigeratorPowerCableWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "refrigerator_power_cable_weights_id")
    private Long alCuRefrigeratorWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public RefrigeratorPowerCableWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
