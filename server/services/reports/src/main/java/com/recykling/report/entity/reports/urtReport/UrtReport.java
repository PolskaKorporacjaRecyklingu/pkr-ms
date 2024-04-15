package com.recykling.report.entity.reports.urtReport;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.ReportBase;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "urt_report_brigade",
            joinColumns = @JoinColumn(name = "urt_report_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> brigade = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "urt_report_lieder",
            joinColumns = @JoinColumn(name = "urt_report_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Employee lieder;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "urt_report_forklift_operator",
            joinColumns = @JoinColumn(name = "urt_report_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Employee forkliftOperator;


    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;

    public void createBrigade(List<Employee> employees){
        employees.forEach(employee -> employee.addToBrigadeMember(this));
        brigade.addAll(employees);
    }
    public void destroyBrigade(){
        brigade.forEach(employee -> employee.removeFromBrigadeMember(this));
        brigade.clear();
    }
    /**
     *
     * @BUILDER
     */
    private UrtReport(ReportBuilder reportBuilder){
        this.setReportData(reportBuilder.reportData);
        this.setEmployeesCount(reportBuilder.employeesCount);
        this.lieder = reportBuilder.lieder;
        this.forkliftOperator = reportBuilder.forkliftOperator;
        this.refrigeratorCount = reportBuilder.refrigeratorCount;
        this.robotWork = reportBuilder.robotWork;
        this.atnWork = reportBuilder.atnWork;
    }
    public static class ReportBuilder{
        private ReportData reportData;
        private Employee lieder;
        private Employee forkliftOperator;
        private EmployeesCount employeesCount;
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;

        public ReportBuilder reportData(ReportData reportData){
            this.reportData = reportData;
            return this;
        }
        public ReportBuilder lieder(Employee lieder){
            this.lieder = lieder;
            return this;
        }
        public ReportBuilder forkliftOperator(Employee forkliftOperator){
            this.forkliftOperator = forkliftOperator;
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
