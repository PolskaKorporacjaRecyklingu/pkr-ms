package com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.repo;

import com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.OilFromAggregatesWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OilFromAggregatesWeightsRepository extends JpaRepository<OilFromAggregatesWeights, Long> {
}
