package com.recykling.report.entity.reports;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "agregates_without_oil")
public class AggregatesWithoutOil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aggregates_id")
    private Long aggregatesId;

    @OneToMany(mappedBy = "aggregatesWithoutOil")
    private List<AggregateWeight> aggregateWeights;
    private Integer packagesCount;
    private Integer sum;

}
