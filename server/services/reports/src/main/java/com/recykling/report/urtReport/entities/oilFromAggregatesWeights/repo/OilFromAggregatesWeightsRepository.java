package com.recykling.report.urtReport.entities.oilFromAggregatesWeights.repo;

import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.OilFromAggregatesWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OilFromAggregatesWeightsRepository extends JpaRepository<OilFromAggregatesWeights, Long> {
}
