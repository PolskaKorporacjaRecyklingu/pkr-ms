package com.recykling.report.urtReport.controller.request;


import com.recykling.report.valueObjects.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    private List<Long> leadersId;

    @NotNull(message = "ForkliftOperatorsId can not be null")
    private List<Long> forkliftOperatorsId;

    @NotNull
    private List<Long> brigadeEmployeesId;

    @Valid @NotNull(message = "RefrigeratorCount can not be null")
    private RefrigeratorCount refrigeratorCount;

    @Valid @NotNull(message = "RobotWork can not be null")
    private RobotWork robotWork;

    @Valid @NotNull(message = "AtnWork can not be null")
    private AtnWork atnWork;

    @Valid @NotNull(message = "Report history cannot be null")
    private List<ReportHistory> reportHistories;

    @Valid @NotNull(message = "AggregatesWithoutOilWeights can not be null")
    private List<Integer> aggregatesWithoutOilWeights;

    @Valid @NotNull(message = "AlCuRefrigeratorWeights can not be null")
    private List<Integer> alCuRefrigeratorWeights;

    @Valid @NotNull(message = "AlCuPackageIncompleteWeight can not be null")
    private Integer alCuPackageIncompleteWeight;

    //  TODO CU RURKI

    @Valid @NotNull(message = "RefrigeratorPowerCableWeights can not be null")
    private List<Integer> refrigeratorPowerCableWeights;

    @Valid @NotNull(message = "OilFromAggregatesWeights can not be null")
    private List<Integer> oilFromAggregatesWeights;

    @Valid @NotNull(message = "PsAbsRefrigeratorWeights can not be null")
    private List<Integer> psAbsRefrigeratorWeights;

    @Valid @NotNull(message = "PsAbsRefrigeratorIncompleteWeight can not be null")
    private Integer psAbsRefrigeratorIncompleteWeight;

    @Valid @NotNull(message = "AggregatesWithOilWeights can not be null")
    private List<Integer> aggregatesWithOilWeights;

    @Valid @NotNull(message = "AluminiumWeights can not be null")
    private List<Integer> aluminiumWeights;

    @Valid @NotNull(message = "AggregatesWithOilFromWarehouseWeights can not be null")
    private List<Integer> aggregatesWithOilFromWarehouseWeights;

}
