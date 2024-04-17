package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Embeddable
public record RefrigeratorCount(
        @PositiveOrZero(message = "RejectedRefrigerator must be positive number or 0")
        @Max(value = 100, message = "Max reworkedRefrigerator for shift is 100")
        @NotNull(message = "RejectedRefrigerator can not be null")
        Integer rejectedRefrigerators,
        @PositiveOrZero(message = "ReworkedRefrigerator must be positive number or 0")
        @Max(value = 700, message = "Max reworkedRefrigerator for shift is 700")
        @NotNull(message = "ReworkedRefrigerator can not be null")
        Integer reworkedRefrigerators
){
        /**
         *
         * @param rejectedRefrigerators - Integer value indicating rejected refrigerators during shift.
         * @param reworkedRefrigerators - Integer value indicating reworked refrigerators during shift.
         */
        public RefrigeratorCount {
        }
}
