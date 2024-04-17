package com.recykling.report.urtReport.entities.urtAggregatesWithOilFromWarehouse.repo;

import com.recykling.report.urtReport.entities.urtAggregatesWithOilFromWarehouse.AggregatesWithOilFromWarehouseWeights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatesWithOilFromWarehouseWeightsRepository extends JpaRepository<AggregatesWithOilFromWarehouseWeights, Long> {
}
