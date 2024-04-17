package com.recykling.report.entity;

import com.recykling.report.entity.reports.UrtReport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity @NoArgsConstructor
@Table(name = "urt_report_history")
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
}
