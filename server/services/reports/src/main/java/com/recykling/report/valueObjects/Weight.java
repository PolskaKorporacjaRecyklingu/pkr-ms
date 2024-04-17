package com.recykling.report.valueObjects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Weight(
        @Min(value = 1, message = "Minimal weight is 1kg")
        @NotNull(message = "Weight can not be null")
        Integer weight
) {
}
