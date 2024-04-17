package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

@Embeddable

public record RobotWork(
        @NotNull(message = "WorkWithRobot can not be null")
        Boolean workWithRobot,
        @Min(value = 0, message = "Minimal time work workWithRobot is 0 hour")
        @Max(value = 8, message = "Maximal time work workWithRobot is 8 hour")
        @NotNull(message = "WorkWithRobotHours can not be null")
        Integer workWithRobotHours) {
    /**
     *
     * @param workWithRobot - Boolean value indicating if URT department were working with robot,
     *      *                 if they were not, automatically workWithRobotHours = 0.
     * @param workWithRobotHours - Integer value indicating how many hours URT department were working with robot.
     */
    public RobotWork {
       workWithRobotHours = workWithRobot ? workWithRobotHours : 0;
    }
}
