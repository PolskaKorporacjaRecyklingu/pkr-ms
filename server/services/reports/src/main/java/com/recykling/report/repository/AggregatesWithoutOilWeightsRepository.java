package com.recykling.report.repository;

import com.recykling.report.entity.reports.AggregatesWithoutOilWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatesWithoutOilWeightsRepository extends JpaRepository<AggregatesWithoutOilWeights, Long> {
}
