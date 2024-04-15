package com.recykling.report.dto;

import com.recykling.report.entity.employee.Employee;
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
    private EmployeeDto lieder;
    private EmployeeDto forkliftOperator;
    private EmployeesCount employeesCount;
    private RefrigeratorCount refrigeratorCount;
    private RobotWork robotWork;
    private AtnWork atnWork;
    private List<EmployeeDto> brigade;

    /**
     * @BUILDER
     */
    private UrtReportDto(UrtReportDtoBuilder urtReportDtoBuilder){
        this.reportData = urtReportDtoBuilder.reportData;
        this.lieder = urtReportDtoBuilder.lieder;
        this.forkliftOperator = urtReportDtoBuilder.forkliftOperator;
        this.employeesCount = urtReportDtoBuilder.employeesCount;
        this.refrigeratorCount = urtReportDtoBuilder.refrigeratorCount;
        this.robotWork = urtReportDtoBuilder.robotWork;
        this.atnWork = urtReportDtoBuilder.atnWork;
        this.brigade = urtReportDtoBuilder.brigade;
    }
    public static class UrtReportDtoBuilder{
        private ReportData reportData;
        private EmployeeDto lieder;
        private EmployeeDto forkliftOperator;
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private EmployeesCount employeesCount;
        private List<EmployeeDto> brigade = new ArrayList<>();
        public UrtReportDtoBuilder reportData(ReportData reportData){
            this.reportData = reportData;
            return this;
        }
        public UrtReportDtoBuilder lieder(Employee lieder){
            this.lieder = new EmployeeDto.EmployeeDtoBuilder()
                    .employeeId(lieder.getEmployeeId())
                    .active(lieder.getActive())
                    .fullName(lieder.getFullName())
                    .build();
            return this;
        }
        public UrtReportDtoBuilder forkliftOperator(Employee forkliftOperator){
            this.forkliftOperator = new EmployeeDto.EmployeeDtoBuilder()
                    .employeeId(forkliftOperator.getEmployeeId())
                    .active(forkliftOperator.getActive())
                    .fullName(forkliftOperator.getFullName())
                    .build();
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
        public UrtReportDtoBuilder brigade(List<Employee> brigade){
            brigade.forEach(employee ->
                    this.brigade.add(
                            new EmployeeDto.EmployeeDtoBuilder()
                                    .fullName(employee.getFullName())
                                    .active(employee.getActive())
                                    .employeeId(employee.getEmployeeId())
                                    .build()));

            return this;
        }
        public UrtReportDto build(){
            return new UrtReportDto(this);
        }
    }
}
