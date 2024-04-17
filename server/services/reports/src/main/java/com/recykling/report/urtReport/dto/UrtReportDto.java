package com.recykling.report.urtReport.dto;

import com.recykling.report.urtReport.entities.alCuRefrigerator.AlCuRefrigeratorWeights;
import com.recykling.report.urtReport.entities.alCuRefrigerator.dto.AlCuRefrigeratorWeightsDto;
import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.OilFromAggregatesWeights;
import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.dto.OilFromAggregatesWeightsDto;
import com.recykling.report.urtReport.entities.psAbsRefrigeratorWeights.PsAbsRefrigeratorWeights;
import com.recykling.report.urtReport.entities.psAbsRefrigeratorWeights.dto.PsAbsRefrigeratorWeightsDto;
import com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.dto.RefrigeratorPowerCableWeightsDto;
import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.dto.AggregatesWithoutOilDto;
import com.recykling.report.employee.dto.EmployeeDto;
import com.recykling.report.urtReport.entities.urtReportHistory.UrtReportHistory;
import com.recykling.report.employee.Employee;
import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.AggregatesWithoutOilWeights;
import com.recykling.report.valueObjects.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WiniaR21
 * @apiNote This dto object is response, validation do not needed
 */
@Data
@NoArgsConstructor
public class UrtReportDto {

    private ReportDate reportDate;
    private List<EmployeeDto> leaders;
    private List<EmployeeDto> forkliftOperators;
    private List<EmployeeDto> brigade;
    private Integer employeesCount;
    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;
    private List<ReportHistory> reportHistories;
    private AggregatesWithoutOilDto aggregatesWithoutOil;
    private AlCuRefrigeratorWeightsDto alCuRefrigeratorWeights;
    private RefrigeratorPowerCableWeightsDto refrigeratorPowerCableWeights;
    private OilFromAggregatesWeightsDto oilFromAggregatesWeights;
    private PsAbsRefrigeratorWeightsDto psAbsRefrigeratorWeights;
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
        this.aggregatesWithoutOil = urtReportDtoBuilder.aggregatesWithoutOil;
        this.alCuRefrigeratorWeights = urtReportDtoBuilder.alCuRefrigeratorWeights;
        this.refrigeratorPowerCableWeights = urtReportDtoBuilder.refrigeratorPowerCableWeights;
        this.oilFromAggregatesWeights = urtReportDtoBuilder.oilFromAggregatesWeights;
        this.psAbsRefrigeratorWeights = urtReportDtoBuilder.psAbsRefrigeratorWeights;
    }
    public static class UrtReportDtoBuilder{
        private ReportDate reportDate;
        private final List<EmployeeDto> leaders = new ArrayList<>();
        private final List<EmployeeDto> forkliftOperators = new ArrayList<>();
        private final List<EmployeeDto> brigade = new ArrayList<>();
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private Integer employeesCount;
        private AggregatesWithoutOilDto aggregatesWithoutOil;
        private List<ReportHistory> reportHistories = new ArrayList<>();
        private AlCuRefrigeratorWeightsDto alCuRefrigeratorWeights;
        private RefrigeratorPowerCableWeightsDto refrigeratorPowerCableWeights;
        private OilFromAggregatesWeightsDto oilFromAggregatesWeights;
        private PsAbsRefrigeratorWeightsDto psAbsRefrigeratorWeights;

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
        public UrtReportDtoBuilder leaders(List<Employee> leaders){
            leaders.forEach(leader -> this.leaders
                    .add(new EmployeeDto.EmployeeDtoBuilder()
                            .employeeId(leader.getEmployeeId())
                            .fullName(leader.getFullName())
                            .active(leader.getActive())
                            .build()));
            return this;
        }
        /**
         *
         * @param forkliftOperators - Input List of Employee objects.
         * @apiNote - Function maps all the Employee into EmployeeDto and saving the into forkliftOperators.
         */
        public UrtReportDtoBuilder forkliftOperators(List<Employee> forkliftOperators){
            forkliftOperators.forEach(forkliftOperator -> this.forkliftOperators
                    .add(new EmployeeDto.EmployeeDtoBuilder()
                            .employeeId(forkliftOperator.getEmployeeId())
                            .fullName(forkliftOperator.getFullName())
                            .active(forkliftOperator.getActive())
                            .build()));
            return this;
        }
        /**
         *
         * @param brigade - Input List of Employee objects.
         * @apiNote - Function maps all the Employee into EmployeeDto and saving the into brigade.
         */
        public UrtReportDtoBuilder brigade(List<Employee> brigade){
            brigade.forEach(brigadeMember -> this.brigade
                    .add(new EmployeeDto.EmployeeDtoBuilder()
                            .employeeId(brigadeMember.getEmployeeId())
                            .fullName(brigadeMember.getFullName())
                            .active(brigadeMember.getActive())
                            .build()));
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
        public UrtReportDtoBuilder reportHistories(List<UrtReportHistory> urtReportHistories){
            urtReportHistories.forEach(history ->
                    reportHistories.add(new ReportHistory(history.getTime(),history.getInfo())));
            return this;
        }
        public UrtReportDtoBuilder aggregatesWithoutOil(List<AggregatesWithoutOilWeights> weights){
            this.aggregatesWithoutOil = new AggregatesWithoutOilDto(weights);
            return this;
        }
        public UrtReportDto build(){
            return new UrtReportDto(this);
        }
    }
}
