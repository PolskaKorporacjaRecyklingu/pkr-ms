package com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.repo;

import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.AggregatesWithoutOilWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatesWithoutOilWeightsRepository extends JpaRepository<AggregatesWithoutOilWeights, Long> {
}
