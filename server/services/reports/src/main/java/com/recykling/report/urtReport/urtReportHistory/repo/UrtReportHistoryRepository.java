package com.recykling.report.urtReport.urtReportHistory.repo;

import com.recykling.report.urtReport.urtReportHistory.UrtReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrtReportHistoryRepository extends JpaRepository<UrtReportHistory, Long> {

}
