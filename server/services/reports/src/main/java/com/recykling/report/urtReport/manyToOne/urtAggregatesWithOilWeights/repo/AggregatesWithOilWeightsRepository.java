package com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.repo;

import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.AggregatesWithOilWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatesWithOilWeightsRepository extends JpaRepository<AggregatesWithOilWeights, Long> {
}
