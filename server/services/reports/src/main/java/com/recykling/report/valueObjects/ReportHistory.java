package com.recykling.report.valueObjects;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ReportHistory(
        @NotNull(message = "Time can not be null")
        LocalTime time,
        @NotNull(message = "Info can not be null")
        String info
) {
}
