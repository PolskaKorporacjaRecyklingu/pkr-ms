package com.recykling.report.urtReport.entities.urtRefrigeratorPowerCableWeights.repo;

import com.recykling.report.urtReport.entities.urtRefrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorPowerCableWeightsRepository extends JpaRepository<RefrigeratorPowerCableWeights, Long> {
}
