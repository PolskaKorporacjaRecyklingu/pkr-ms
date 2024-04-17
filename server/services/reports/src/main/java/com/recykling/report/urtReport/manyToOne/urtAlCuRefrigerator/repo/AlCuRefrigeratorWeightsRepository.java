package com.recykling.report.urtReport.manyToOne.urtAlCuRefrigerator.repo;

import com.recykling.report.urtReport.manyToOne.urtAlCuRefrigerator.AlCuRefrigeratorWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlCuRefrigeratorWeightsRepository extends JpaRepository<AlCuRefrigeratorWeights, Long> {
}
