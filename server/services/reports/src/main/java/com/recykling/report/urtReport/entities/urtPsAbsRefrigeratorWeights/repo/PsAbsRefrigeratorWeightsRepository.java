package com.recykling.report.urtReport.entities.urtPsAbsRefrigeratorWeights.repo;

import com.recykling.report.urtReport.entities.urtPsAbsRefrigeratorWeights.PsAbsRefrigeratorWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsAbsRefrigeratorWeightsRepository extends JpaRepository<PsAbsRefrigeratorWeights, Long> {
}
