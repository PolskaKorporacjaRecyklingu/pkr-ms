package com.recykling.report.request;


import com.recykling.report.valueObjects.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author WiniaR21
 */
@Data
@NoArgsConstructor
public class RequestCreateUrtReport {
    @Valid
    @NotNull(message = "ReportData can not be null")
    private ReportDate reportDate;

    @NotNull(message = "LiederId can not be null")
    private List<Long> leadersId;

    @NotNull(message = "ForkliftOperatorsId can not be null")
    private List<Long> forkliftOperatorsId;

    @NotNull
    private List<Long> brigadeEmployeesIdList;

    @Valid @NotNull(message = "RefrigeratorCount can not be null")
    private RefrigeratorCount refrigeratorCount;

    @Valid @NotNull(message = "RobotWork can not be null")
    private RobotWork robotWork;

    @Valid @NotNull(message = "AtnWork can not be null")
    private AtnWork atnWork;

    @Valid @NotNull(message = "Report history cannot be null")
    private List<ReportHistory> reportHistories;
}
