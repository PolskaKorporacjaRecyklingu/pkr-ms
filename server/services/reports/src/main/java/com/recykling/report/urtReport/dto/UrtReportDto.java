package com.recykling.report.urtReport.dto;

import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilFromWarehouse.*;
import com.recykling.report.urtReport.manyToOne.urtAggregatesWithOilWeights.*;
import com.recykling.report.urtReport.manyToOne.urtAlCuRefrigerator.*;
import com.recykling.report.urtReport.manyToOne.urtAluminium.*;
import com.recykling.report.urtReport.manyToOne.urtOilFromAggregatesWeights.*;
import com.recykling.report.urtReport.manyToOne.urtPsAbsRefrigeratorWeights.*;
import com.recykling.report.urtReport.manyToOne.urtRefrigeratorPowerCableWeights.*;
import com.recykling.report.urtReport.manyToOne.urtAggregatesWithoutOilWeights.*;
import com.recykling.report.employee.dto.EmployeeDto;
import com.recykling.report.urtReport.manyToOne.urtReportHistory.UrtReportHistory;
import com.recykling.report.employee.Employee;
import com.recykling.report.valueObjects.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WiniaR21
 * @NOTE - This dto object is response, validation do not needed
 */
@Data
@NoArgsConstructor
public class UrtReportDto {

    private ReportDate reportDate;
    private Set<EmployeeDto> leaders;
    private Set<EmployeeDto> forkliftOperators;
    private Set<EmployeeDto> brigade;
    private Integer employeesCount;
    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;
    private Set<ReportHistory> reportHistories;
    private AggregatesWithoutOilDto aggregatesWithoutOilWeights;
    private AlCuRefrigeratorWeightsDto alCuRefrigeratorWeights;
    private RefrigeratorPowerCableWeightsDto refrigeratorPowerCableWeights;
    private OilFromAggregatesWeightsDto oilFromAggregatesWeights;
    private PsAbsRefrigeratorWeightsDto psAbsRefrigeratorWeights;
    private AggregatesWithOilWeightsDto aggregatesWithOilWeights;
    private AluminiumWeightsDto aluminiumWeights;
    private AggregatesWithOilFromWarehouseWeightsDto aggregatesWithOilFromWarehouseWeights;
    /**
     * @BUILDER
     */
    private UrtReportDto(UrtReportDtoBuilder urtReportDtoBuilder){
        this.reportDate = urtReportDtoBuilder.reportDate;
        this.leaders = urtReportDtoBuilder.leaders;
        this.forkliftOperators = urtReportDtoBuilder.forkliftOperators;
        this.employeesCount = urtReportDtoBuilder.employeesCount;
        this.refrigeratorCount = urtReportDtoBuilder.refrigeratorCount;
        this.robotWork = urtReportDtoBuilder.robotWork;
        this.atnWork = urtReportDtoBuilder.atnWork;
        this.brigade = urtReportDtoBuilder.brigade;
        this.reportHistories = urtReportDtoBuilder.reportHistories;
        this.aggregatesWithoutOilWeights = urtReportDtoBuilder.aggregatesWithoutOilWeights;
        this.alCuRefrigeratorWeights = urtReportDtoBuilder.alCuRefrigeratorWeights;
        this.refrigeratorPowerCableWeights = urtReportDtoBuilder.refrigeratorPowerCableWeights;
        this.oilFromAggregatesWeights = urtReportDtoBuilder.oilFromAggregatesWeights;
        this.psAbsRefrigeratorWeights = urtReportDtoBuilder.psAbsRefrigeratorWeights;
        this.aggregatesWithOilWeights = urtReportDtoBuilder.aggregatesWithOilWeights;
        this.aluminiumWeights = urtReportDtoBuilder.aluminiumWeights;
        this.aggregatesWithOilFromWarehouseWeights = urtReportDtoBuilder.aggregatesWithOilFromWarehouseWeights;
    }
    public static class UrtReportDtoBuilder{
        private ReportDate reportDate;
        private final Set<EmployeeDto> leaders = new HashSet<>();
        private final Set<EmployeeDto> forkliftOperators = new HashSet<>();
        private final Set<EmployeeDto> brigade = new HashSet<>();
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private Integer employeesCount;
        private AggregatesWithoutOilDto aggregatesWithoutOilWeights;
        private Set<ReportHistory> reportHistories = new HashSet<>();
        private AlCuRefrigeratorWeightsDto alCuRefrigeratorWeights;
        private RefrigeratorPowerCableWeightsDto refrigeratorPowerCableWeights;
        private OilFromAggregatesWeightsDto oilFromAggregatesWeights;
        private PsAbsRefrigeratorWeightsDto psAbsRefrigeratorWeights;
        private AggregatesWithOilWeightsDto aggregatesWithOilWeights;
        private AluminiumWeightsDto aluminiumWeights;
        private AggregatesWithOilFromWarehouseWeightsDto aggregatesWithOilFromWarehouseWeights;


        public UrtReportDtoBuilder aggregatesWithOilWeights(List<AggregatesWithOilWeights> weights){
            this.aggregatesWithOilWeights = new AggregatesWithOilWeightsDto(weights);
            return this;
        }
        public UrtReportDtoBuilder aluminiumWeights(List<AluminiumWeights> weights){
            this.aluminiumWeights = new AluminiumWeightsDto(weights);
            return this;
        }
        public UrtReportDtoBuilder aggregatesWithOilFromWarehouseWeights(List<AggregatesWithOilFromWarehouseWeights> weights){
            this.aggregatesWithOilFromWarehouseWeights = new AggregatesWithOilFromWarehouseWeightsDto(weights);
            return this;
        }
        public UrtReportDtoBuilder psAbsRefrigeratorWeights(List<PsAbsRefrigeratorWeights> weights, Integer incomplete){
            this.psAbsRefrigeratorWeights = new PsAbsRefrigeratorWeightsDto(weights, incomplete);
            return this;
        }
        public UrtReportDtoBuilder alCuRefrigeratorWeights(List<AlCuRefrigeratorWeights> weights, Integer incomplete){
            this.alCuRefrigeratorWeights = new AlCuRefrigeratorWeightsDto(weights, incomplete);
            return this;
        }
        public UrtReportDtoBuilder refrigeratorPowerCableWeights(List<RefrigeratorPowerCableWeights> weights){
            this.refrigeratorPowerCableWeights = new RefrigeratorPowerCableWeightsDto(weights);
            return this;
        }
        public UrtReportDtoBuilder oilFromAggregatesWeights(List<OilFromAggregatesWeights> weights){
            this.oilFromAggregatesWeights = new OilFromAggregatesWeightsDto(weights);
            return this;
        }
        /**
         *
         * @param leaders - Input List of Employee objects.
         * @apiNote - Function maps all the Employee into EmployeeDto and saving the into leaders.
         */
        public UrtReportDtoBuilder leaders(Set<Employee> leaders){
            leaders.forEach(leader -> this.leaders
                    .add(buildEmployeeDto(leader)));
            return this;
        }
        /**
         *
         * @param forkliftOperators - Input List of Employee objects.
         * @apiNote - Function maps all the Employee into EmployeeDto and saving the into forkliftOperators.
         */
        public UrtReportDtoBuilder forkliftOperators(Set<Employee> forkliftOperators){
            forkliftOperators.forEach(forkliftOperator -> this.forkliftOperators
                    .add(buildEmployeeDto(forkliftOperator)));
            return this;
        }
        /**
         *
         * @param brigade - Input List of Employee objects.
         * @apiNote - Function maps all the Employee into EmployeeDto and saving the into brigade.
         */
        public UrtReportDtoBuilder brigade(Set<Employee> brigade){
            brigade.forEach(brigadeMember -> this.brigade
                    .add(buildEmployeeDto(brigadeMember)));
            return this;
        }
        public UrtReportDtoBuilder reportData(ReportDate reportDate){
            this.reportDate = reportDate;
            return this;
        }
        public UrtReportDtoBuilder employeesCount(EmployeesCount employeesCount){
            this.employeesCount = employeesCount.employeesCount();
            return this;
        }
        public UrtReportDtoBuilder refrigeratorCount(RefrigeratorCount refrigeratorCount){
            this.refrigeratorCount = refrigeratorCount;
            return this;
        }
        public UrtReportDtoBuilder robotWork(RobotWork robotWork){
            this.robotWork = robotWork;
            return this;
        }
        public UrtReportDtoBuilder atnWork(AtnWork atnWork){
            this.atnWork = atnWork;
            return this;
        }
        public UrtReportDtoBuilder reportHistories(Set<UrtReportHistory> urtReportHistories){
            urtReportHistories.forEach(history ->
                    reportHistories.add(new ReportHistory(history.getTime(),history.getInfo())));
            return this;
        }
        public UrtReportDtoBuilder aggregatesWithoutOilWeights(List<AggregatesWithoutOilWeights> weights){
            this.aggregatesWithoutOilWeights = new AggregatesWithoutOilDto(weights);
            return this;
        }
        public UrtReportDto build(){
            return new UrtReportDto(this);
        }

        private EmployeeDto buildEmployeeDto(Employee employee){
            return  new EmployeeDto.EmployeeDtoBuilder()
                    .employeeId(employee.getEmployeeId())
                    .fullName(employee.getFullName())
                    .active(employee.getActive())
                    .hasAccount(employee.getHasAccount())
                    .build();
        }
    }
}
