package com.recykling.report.urtReport.repo;

import com.recykling.report.urtReport.UrtReport;
import com.recykling.report.valueObjects.ReportDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author WiniaR21
 */
@Repository
public interface UrtReportRepository extends JpaRepository<UrtReport, Long> {
    Optional<UrtReport> findByReportDate(ReportDate reportDate);
}
