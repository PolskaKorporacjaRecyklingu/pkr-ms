package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Embeddable
public record ReportDate(
        @NotNull(message = "Date can not be null")
        LocalDate date,

        @NotNull(message = "Shift can not be null")
        @Min(value = 1, message = "There are 3 shifts")
        @Max(value = 3, message = "There are 3 shifts")
        Integer shift
){
        /**
         *
         * @param date - Date of report.
         * @param shift - Shift of report.
         */
        public ReportDate {
        }
}
