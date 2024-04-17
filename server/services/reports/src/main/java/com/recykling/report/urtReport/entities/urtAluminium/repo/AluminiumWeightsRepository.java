package com.recykling.report.urtReport.entities.urtAluminium.repo;

import com.recykling.report.urtReport.entities.urtAluminium.AluminiumWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluminiumWeightsRepository extends JpaRepository<AluminiumWeights, Long> {
}
