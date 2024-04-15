package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record EmployeesCount(
        @NotNull(message = "EmployeesCount can not be null")
        @Min(value = 2, message = "Minimal employeesCount is 2")
        @Max(value = 30, message = "Maximal employeesCount is 20")
        Integer employeesCount
){
        /**
         *
         * @param employeesCount - Integer value indicating employees count on shift.
         */
        public EmployeesCount {
        }
}
