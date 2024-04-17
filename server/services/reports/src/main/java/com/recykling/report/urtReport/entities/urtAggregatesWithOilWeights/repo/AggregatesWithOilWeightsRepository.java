package com.recykling.report.urtReport.entities.urtAggregatesWithOilWeights.repo;

import com.recykling.report.urtReport.entities.urtAggregatesWithOilWeights.AggregatesWithOilWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatesWithOilWeightsRepository extends JpaRepository<AggregatesWithOilWeights, Long> {
}
