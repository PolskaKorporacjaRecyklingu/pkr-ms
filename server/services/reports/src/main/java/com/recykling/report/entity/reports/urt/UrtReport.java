package com.recykling.report.entity.reports.urt;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.ReportBase;
import com.recykling.report.entity.reports.urt.brigade.UrtBrigadeMember;
import com.recykling.report.entity.reports.urt.brigade.UrtForkliftOperator;
import com.recykling.report.entity.reports.urt.brigade.UrtReportLieder;
import com.recykling.report.repository.UrtReportRepository;
import com.recykling.report.valueObjects.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WiniaR21
 */
@Entity @Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urt_reports")
public class UrtReport extends ReportBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "urt_report_id")
    private Long urtReportId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urtReport")
    private List<UrtBrigadeMember> brigade = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urtReport")
    private List<UrtReportLieder> leaders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urtReport")
    private List<UrtForkliftOperator> forkliftOperators = new ArrayList<>();

    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;

    /**
     *
     * @BUILDER
     */
    private UrtReport(ReportBuilder reportBuilder){
        this.setReportData(reportBuilder.reportData);
        this.setEmployeesCount(reportBuilder.employeesCount);
        this.refrigeratorCount = reportBuilder.refrigeratorCount;
        this.robotWork = reportBuilder.robotWork;
        this.atnWork = reportBuilder.atnWork;
    }
    @RequiredArgsConstructor
    public static class ReportBuilder{
        private ReportData reportData;
        private EmployeesCount employeesCount;
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;

        private final UrtReportRepository urtReportRepository;

        public ReportBuilder reportData(ReportData reportData){
            this.reportData = reportData;
            return this;
        }
        public ReportBuilder employeesCount(EmployeesCount employeesCount){
            this.employeesCount = employeesCount;
            return this;
        }
        public ReportBuilder refrigeratorCount(RefrigeratorCount refrigeratorCount){
            this.refrigeratorCount = refrigeratorCount;
            return this;
        }
        public ReportBuilder robotWork(RobotWork robotWork){
            this.robotWork = robotWork;
            return this;
        }
        public ReportBuilder atnWork(AtnWork atnWork){
            this.atnWork = atnWork;
            return this;
        }
        public UrtReport build(){
            return new UrtReport(this);
        }
    }
}
