package com.recykling.report.dto;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.valueObjects.AtnWork;
import com.recykling.report.valueObjects.RefrigeratorCount;
import com.recykling.report.valueObjects.RobotWork;
import com.recykling.report.valueObjects.EmployeesCount;
import com.recykling.report.valueObjects.ReportDate;
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

    private ReportDate reportDate;
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
        this.reportDate = urtReportDtoBuilder.reportDate;
        this.leaders = urtReportDtoBuilder.leaders;
        this.forkliftOperators = urtReportDtoBuilder.forkliftOperators;
        this.employeesCount = urtReportDtoBuilder.employeesCount;
        this.refrigeratorCount = urtReportDtoBuilder.refrigeratorCount;
        this.robotWork = urtReportDtoBuilder.robotWork;
        this.atnWork = urtReportDtoBuilder.atnWork;
        this.brigade = urtReportDtoBuilder.brigade;
    }
    public static class UrtReportDtoBuilder{
        private ReportDate reportDate;
        private final List<EmployeeDto> leaders = new ArrayList<>();
        private final List<EmployeeDto> forkliftOperators = new ArrayList<>();
        private final List<EmployeeDto> brigade = new ArrayList<>();
        private RefrigeratorCount refrigeratorCount;
        private RobotWork robotWork;
        private AtnWork atnWork;
        private EmployeesCount employeesCount;

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
