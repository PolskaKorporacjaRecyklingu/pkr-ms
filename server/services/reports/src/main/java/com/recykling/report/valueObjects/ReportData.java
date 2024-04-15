package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record ReportData(
        @Min(value = 2020, message = "Possible years are between 2020-2050")
        @Max(value = 2050, message = "Possible years are between 2020-2050")
        @NotNull(message = "Year can not be null")
        Integer year,
        @Min(value = 1, message = "Possible months are between 1-12")
        @Max(value = 12, message = "Possible months are between 1-12")
        @NotNull(message = "Month can not be null")
        Integer month,
        @Min(value = 1, message = "Possible days are between 1-31")
        @Max(value = 31, message = "Possible days are between 1-31")
        @NotNull(message = "Day can not be null")
        Integer day,
        @Valid @NotNull(message = "Shift can not be null")
        Shift shift
){
        /**
         *
         * @param year - Year of report.
         * @param month - Month of report.
         * @param day - Day of report.
         * @param shift - Shift of report.
         */
        public ReportData {
        }
}
