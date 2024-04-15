package com.recykling.report.dto;

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

    private EmployeeDto(EmployeeDtoBuilder employeeDtoBuilder){
        this.employeeId = employeeDtoBuilder.employeeId;
        this.fullName = employeeDtoBuilder.fullName;
        this.active = employeeDtoBuilder.active;
    }

    /**
     * @BUILDER
     */
    public static class EmployeeDtoBuilder{
        private Long employeeId;
        private FullName fullName;
        private Boolean active;
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

        public EmployeeDto build(){
            return new EmployeeDto(this);
        }
    }
}
