package com.recykling.report.urtReport.entities.urtAluminium;

import com.recykling.report.urtReport.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urt_aluminium_weights")
@NoArgsConstructor
@Getter
public class AluminiumWeights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "urt_aluminium_weights_id")
    private Long aluminiumWeightsId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public AluminiumWeights(Integer weight, UrtReport urtReport) {
        this.weight = weight;
        this.urtReport = urtReport;
    }
}
