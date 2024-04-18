package com.recykling.report.urtReport;

import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilFromWarehouse.AggregatesWithOilFromWarehouseWeights;
import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.AggregatesWithOilWeights;
import com.recykling.report.urtReport.manyToOne.urtAlCuRefrigerator.AlCuRefrigeratorWeights;
import com.recykling.report.urtReport.manyToOne.urtAluminium.AluminiumWeights;
import com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.OilFromAggregatesWeights;
import com.recykling.report.urtReport.manyToOne.urtPsAbsRefrigeratorWeights.PsAbsRefrigeratorWeights;
import com.recykling.report.urtReport.manyToOne.urtRefrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import com.recykling.report.urtReport.manyToOne.urtReportHistory.UrtReportHistory;
import com.recykling.report.urtReport.manyToOne.urtAggregatesWithoutOilWeights.AggregatesWithoutOilWeights;
import com.recykling.report.employee.Employee;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.employee.repo.EmployeeRepository;
import com.recykling.report.valueObjects.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @ManyToMany
    @JoinTable(
            name = "urt_brigade",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "urt_report_id"))
    private List<Employee> brigade = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "urt_leaders",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "urt_report_id"))
    private List<Employee> leaders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "urt_forklift_operators",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "urt_report_id"))
    private List<Employee> forkliftOperators = new ArrayList<>();

    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<UrtReportHistory> urtReportHistories = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<AggregatesWithoutOilWeights> aggregatesWithoutOilWeights = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<AlCuRefrigeratorWeights> alCuRefrigeratorWeights = new ArrayList<>();
    private Integer alCuPackageIncompleteWeight;

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<RefrigeratorPowerCableWeights> refrigeratorPowerCableWeights = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<OilFromAggregatesWeights> oilFromAggregatesWeights = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<PsAbsRefrigeratorWeights> psAbsRefrigeratorWeights = new ArrayList<>();
    private Integer psAbsRefrigeratorIncompleteWeight;

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<AggregatesWithOilWeights> aggregatesWithOilWeights = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<AluminiumWeights> aluminiumWeights = new ArrayList<>();

    @OneToMany(mappedBy = "urtReport", cascade = CascadeType.ALL)
    private List<AggregatesWithOilFromWarehouseWeights> aggregatesWithOilFromWarehouseWeights = new ArrayList<>();

    public void setupUrtReportHistories(List<ReportHistory> history){
        history.forEach(h -> urtReportHistories.add(new UrtReportHistory(h, this)));
    }
    public void setupAggregatesWithoutOilWeights(List<Integer> weights){
        weights.forEach(w -> aggregatesWithoutOilWeights.add(new AggregatesWithoutOilWeights(w, this)));
    }
    public void setupAlCuRefrigeratorWeights(List<Integer> weights){
        weights.forEach(w -> alCuRefrigeratorWeights.add(new AlCuRefrigeratorWeights(w, this)));
    }
    public void setupRefrigeratorPowerCableWeights(List<Integer> weights){
        weights.forEach(w -> refrigeratorPowerCableWeights.add(new RefrigeratorPowerCableWeights(w, this)));
    }
    public void setupOilFromAggregatesWeights(List<Integer> weights){
        weights.forEach(w -> oilFromAggregatesWeights.add(new OilFromAggregatesWeights(w, this)));
    }
    public void setupPsAbsRefrigeratorWeights(List<Integer> weights){
        weights.forEach(w -> psAbsRefrigeratorWeights.add(new PsAbsRefrigeratorWeights(w, this)));
    }
    public void setupAggregatesWithOilWeights(List<Integer> weights){
        weights.forEach(w -> aggregatesWithOilWeights.add(new AggregatesWithOilWeights(w, this)));
    }
    public void setupAluminiumWeights(List<Integer> weights){
        weights.forEach(w -> aluminiumWeights.add(new AluminiumWeights(w, this)));
    }
    public void setupAggregatesWithOilFromWarehouseWeights(List<Integer> weights){
        weights.forEach(w -> aggregatesWithOilFromWarehouseWeights.add(new AggregatesWithOilFromWarehouseWeights(w, this)));
    }
    /**
     *
     * @BUILDER
     */
    private UrtReport(ReportBuilder reportBuilder){
        this.setReportDate(reportBuilder.reportDate);
        this.brigade = reportBuilder.brigade;
        this.leaders = reportBuilder.leaders;
        this.forkliftOperators = reportBuilder.forkliftOperators;
        this.refrigeratorCount = reportBuilder.refrigeratorCount;
        this.robotWork = reportBuilder.robotWork;
        this.atnWork = reportBuilder.atnWork;
        this.alCuPackageIncompleteWeight = reportBuilder.alCuPackageIncompleteWeight;
        this.psAbsRefrigeratorIncompleteWeight = reportBuilder.psAbsRefrigeratorIncompleteWeight;

        this.setEmployeesCount(new EmployeesCount(brigade.size()));
    }
    @RequiredArgsConstructor
    public static class ReportBuilder{
        private ReportDate reportDate;
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private List<Employee> brigade = new ArrayList<>();
        private List<Employee> leaders = new ArrayList<>();
        private Integer alCuPackageIncompleteWeight;
        private List<Employee> forkliftOperators = new ArrayList<>();
        //  TODO tutaj był null
        private Integer psAbsRefrigeratorIncompleteWeight;

        private final EmployeeRepository employeeRepository;
        public ReportBuilder psAbsRefrigeratorIncompleteWeight(Integer psAbsRefrigeratorIncompleteWeight){
            this.psAbsRefrigeratorIncompleteWeight = psAbsRefrigeratorIncompleteWeight;
            return this;
        }
        public ReportBuilder reportData(ReportDate reportDate){
            this.reportDate = reportDate;
            return this;
        }
        public ReportBuilder alCuPackageIncomplete(Integer alCuPackageIncompleteWeight){
            this.alCuPackageIncompleteWeight = alCuPackageIncompleteWeight;
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
        public ReportBuilder brigade(List<Long> employeesId){
            this.brigade = findListOfEmployees(employeesId, "brigade");
            return this;
        }
        public ReportBuilder leaders(List<Long> leadersId){
            this.leaders = findListOfEmployees(leadersId, "leaders");
            return this;
        }
        public ReportBuilder forkliftOperators(List<Long> forkliftOperatorsId){
            this.forkliftOperators = findListOfEmployees(forkliftOperatorsId, "forkliftOperators");
            return this;
        }

        /**
         *
         * @param employeesId - EmployeesId list received from client in RequestCreateUrtReport.
         * @param fieldName - Field name where returned data will be saved.
         * @return - Returns array list of employee objects
         */
        private List<Employee> findListOfEmployees(List<Long> employeesId, String fieldName){
            List<Employee> employees = new ArrayList<>();

            employeesId.forEach(employeeId ->{
                Optional<Employee> employee = employeeRepository.findById(employeeId);
                if (employee.isEmpty()){
                    throw new ResourceNotFoundException("Employee", fieldName, employeeId.toString());
                }else {
                    employees.add(employee.get());
                }
            });

            return employees;
        }
        public UrtReport build(){
            return new UrtReport(this);
        }
    }
}
