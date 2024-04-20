package com.recykling.report.employee.dto;

import com.recykling.report.valueObjects.FullName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WiniaR21
 */
@Data
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    private Boolean active;
    private FullName fullName;
    private Boolean hasAccount;

    private EmployeeDto(EmployeeDtoBuilder employeeDtoBuilder){
        this.employeeId = employeeDtoBuilder.employeeId;
        this.fullName = employeeDtoBuilder.fullName;
        this.active = employeeDtoBuilder.active;
        this.hasAccount = employeeDtoBuilder.hasAccount;
    }

    /**
     * @BUILDER
     */
    public static class EmployeeDtoBuilder{
        private Long employeeId;
        private FullName fullName;
        private Boolean active;
        private Boolean hasAccount;
        public EmployeeDtoBuilder employeeId(Long employeeId){
            this.employeeId = employeeId;
            return this;
        }
        public EmployeeDtoBuilder fullName(FullName fullName){
            this.fullName = fullName;
            return this;
        }
        public EmployeeDtoBuilder active(Boolean active){
            this.active = active;
            return this;
        }
        public EmployeeDtoBuilder hasAccount(Boolean hasAccount){
            this.hasAccount = hasAccount;
            return this;
        }

        public EmployeeDto build(){
            return new EmployeeDto(this);
        }
    }
}
