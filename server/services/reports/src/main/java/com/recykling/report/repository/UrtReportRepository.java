package com.recykling.report.repository;

import com.recykling.report.entity.reports.urt.UrtReport;
import com.recykling.report.valueObjects.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author WiniaR21
 */
@Repository
public interface UrtReportRepository extends JpaRepository<UrtReport, Long> {
    Optional<UrtReport> findByReportData(ReportData reportData);
}
