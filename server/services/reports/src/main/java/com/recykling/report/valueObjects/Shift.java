package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Shift(
        @NotNull(message = "Shift can not be null")
        @Min(value = 1, message = "There are 3 shifts")
        @Max(value = 3, message = "There are 3 shifts")
        Integer shift
){
        /**
         *
         * @param shift - Integer indicating shift. There are 3 shifts.
         */
        public Shift {
        }
}
