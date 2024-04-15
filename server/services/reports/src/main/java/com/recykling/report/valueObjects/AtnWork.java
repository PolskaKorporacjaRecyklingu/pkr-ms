package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record AtnWork(
        @NotNull(message = "WorkWithAtn can not be null")
        Boolean workWithAtn,
        @Min(value = 0, message = "Minimal time work workWithAtnHours is 0 hour")
        @Max(value = 8, message = "Maximal time work workWithAtnHours is 8 hour")
        @NotNull(message = "WorkWithAtnHours can not be null")
        Integer workWithAtnHours
){
    /**
     *
     * @param workWithAtn - Boolean value indicating if URT department were working with ATN,
     *                      if they were not, automatically workWithATN = 0.
     * @param workWithAtnHours - Integer value indicating how many hours URT department were working with ATN.
     */
    public AtnWork {
        workWithAtnHours = workWithAtn ? workWithAtnHours : 0;
    }
}
