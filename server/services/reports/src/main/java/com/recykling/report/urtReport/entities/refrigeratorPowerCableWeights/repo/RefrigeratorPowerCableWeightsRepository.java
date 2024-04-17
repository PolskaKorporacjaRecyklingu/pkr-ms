package com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.repo;

import com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorPowerCableWeightsRepository extends JpaRepository<RefrigeratorPowerCableWeights, Long> {
}
