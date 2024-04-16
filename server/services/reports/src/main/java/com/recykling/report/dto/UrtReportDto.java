package com.recykling.report.dto;

import com.recykling.report.entity.reports.urt.brigade.UrtBrigadeMember;
import com.recykling.report.entity.reports.urt.brigade.UrtForkliftOperator;
import com.recykling.report.entity.reports.urt.brigade.UrtReportLieder;
import com.recykling.report.valueObjects.AtnWork;
import com.recykling.report.valueObjects.RefrigeratorCount;
import com.recykling.report.valueObjects.RobotWork;
import com.recykling.report.valueObjects.EmployeesCount;
import com.recykling.report.valueObjects.ReportData;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WiniaR21
 */
@Data
@NoArgsConstructor
public class UrtReportDto {

    private ReportData reportData;
    private List<EmployeeDto> leaders;
    private List<EmployeeDto> forkliftOperators;
    private List<EmployeeDto> brigade;
    private EmployeesCount employeesCount;
    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;

    /**
     * @BUILDER
     */
    private UrtReportDto(UrtReportDtoBuilder urtReportDtoBuilder){
        this.reportData = urtReportDtoBuilder.reportData;
        this.leaders = urtReportDtoBuilder.leaders;
        this.forkliftOperators = urtReportDtoBuilder.forkliftOperators;
        this.employeesCount = urtReportDtoBuilder.employeesCount;
        this.refrigeratorCount = urtReportDtoBuilder.refrigeratorCount;
        this.robotWork = urtReportDtoBuilder.robotWork;
        this.atnWork = urtReportDtoBuilder.atnWork;
        this.brigade = urtReportDtoBuilder.brigade;
    }
    public static class UrtReportDtoBuilder{
        private ReportData reportData;
        private List<EmployeeDto> leaders = new ArrayList<>();
        private List<EmployeeDto> forkliftOperators = new ArrayList<>();
        private List<EmployeeDto> brigade = new ArrayList<>();
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private EmployeesCount employeesCount;
        public UrtReportDtoBuilder reportData(ReportData reportData){
            this.reportData = reportData;
            return this;
        }
        public UrtReportDtoBuilder leaders(List<UrtReportLieder> leaders){
            leaders.forEach(leader ->
                    this.leaders
                            .add(new EmployeeDto.EmployeeDtoBuilder()
                                    .employeeId(leader.getUrtEmployeeId().getEmployeeId())
                                    .fullName(leader.getEmployee().getFullName())
                                    .active(leader.getEmployee().getActive())
                                    .build()));
            return this;
        }
        public UrtReportDtoBuilder forkliftOperator(List<UrtForkliftOperator> forkliftOperators){
            forkliftOperators.forEach(operator ->
                    this.forkliftOperators
                            .add(new EmployeeDto.EmployeeDtoBuilder()
                                    .employeeId(operator.getEmployee().getEmployeeId())
                                    .fullName(operator.getEmployee().getFullName())
                                    .active(operator.getEmployee().getActive())
                                    .build()));
            return this;
        }
        public UrtReportDtoBuilder brigade(List<UrtBrigadeMember> brigade){
            brigade.forEach(brigadeMember ->
                    this.brigade.add(
                            new EmployeeDto.EmployeeDtoBuilder()
                                    .employeeId(brigadeMember.getEmployee().getEmployeeId())
                                    .fullName(brigadeMember.getEmployee().getFullName())
                                    .active(brigadeMember.getEmployee().getActive())
                                    .build()));
            return this;
        }
        public UrtReportDtoBuilder employeesCount(EmployeesCount employeesCount){
            this.employeesCount = employeesCount;
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

        public UrtReportDto build(){
            return new UrtReportDto(this);
        }
    }
}
