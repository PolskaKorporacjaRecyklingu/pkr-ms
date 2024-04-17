package com.recykling.report.urtReport.entities.urtReportHistory;

import com.recykling.report.urtReport.UrtReport;
import com.recykling.report.valueObjects.ReportHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity @NoArgsConstructor
@Table(name = "urt_report_history")
@Getter
public class UrtReportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "history_id")
    private Long historyId;

    @NotNull(message = "Time can not be null")
    private LocalTime time;
    @NotNull(message = "Info can not be null")
    private String info;

    @ManyToOne
    @JoinColumn(name="urt_report_id", nullable = false)
    private UrtReport urtReport;

    public UrtReportHistory(ReportHistory reportHistory, UrtReport urtReport) {
        this.time = reportHistory.time();
        this.info = reportHistory.info();
        this.urtReport = urtReport;
    }
}
