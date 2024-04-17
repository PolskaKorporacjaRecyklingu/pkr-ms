package com.recykling.report.entity.reports;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class AggregateWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregate_weight_id")
    private Long aggregateWeightId;

    private Integer weight;

    @ManyToOne
    @JoinColumn(name="agregate_without_oil_id", nullable = false)
    private AggregatesWithoutOil aggregatesWithoutOil;

    
}
