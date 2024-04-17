package com.recykling.report.repository;

import com.recykling.report.entity.UrtReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrtReportHistoryRepository extends JpaRepository<UrtReportHistory, Long> {

}
