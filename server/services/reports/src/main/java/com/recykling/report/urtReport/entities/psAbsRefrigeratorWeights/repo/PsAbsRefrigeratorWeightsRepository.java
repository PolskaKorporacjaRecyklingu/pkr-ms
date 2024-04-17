package com.recykling.report.urtReport.entities.psAbsRefrigeratorWeights.repo;

import com.recykling.report.urtReport.entities.psAbsRefrigeratorWeights.PsAbsRefrigeratorWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsAbsRefrigeratorWeightsRepository extends JpaRepository<PsAbsRefrigeratorWeights, Long> {
}
