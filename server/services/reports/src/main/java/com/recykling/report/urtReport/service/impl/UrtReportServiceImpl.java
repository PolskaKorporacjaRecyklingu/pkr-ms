package com.recykling.report.urtReport.service.impl;


import com.recykling.report.urtReport.service.IUrtReportService;
import com.recykling.report.urtReport.controller.request.RequestCreateUrtReport;
import com.recykling.report.urtReport.dto.UrtReportDto;
import com.recykling.report.urtReport.UrtReport;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.employee.repo.EmployeeRepository;
import com.recykling.report.urtReport.repo.UrtReportRepository;
import com.recykling.report.valueObjects.ReportDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * @author WiniaR21
 */
@Service
@RequiredArgsConstructor
public class UrtReportServiceImpl implements IUrtReportService {
    private final UrtReportRepository urtReportRepository;
    private final EmployeeRepository employeeRepository;
    /**
     *
     * @param request - Input RequestCreateUrtReport object.
     * @NOTE - Function creates new urtReport based on request.
     *         First step to create is creating this entity with base data,
     *         second step is setting up all important info with are list of data.
     */
    @Override
    public void createReport(RequestCreateUrtReport request) {

        UrtReport urtReport = new UrtReport.ReportBuilder(employeeRepository)
                .reportData(request.getReportDate())
                .leaders(request.getLeadersId())
                .forkliftOperators(request.getForkliftOperatorsId())
                .brigade(request.getBrigadeEmployeesId())
                .refrigeratorCount(request.getRefrigeratorCount())
                .robotWork(request.getRobotWork())
                .atnWork(request.getAtnWork())
                .alCuPackageIncomplete(request.getAlCuPackageIncompleteWeight())
                .psAbsRefrigeratorIncompleteWeight(request.getPsAbsRefrigeratorIncompleteWeight())
                .build();

        urtReport.setupUrtReportHistories(request.getReportHistories());
        urtReport.setupAggregatesWithoutOilWeights(request.getAggregatesWithoutOilWeights());
        urtReport.setupAlCuRefrigeratorWeights(request.getAlCuRefrigeratorWeights());
        urtReport.setupRefrigeratorPowerCableWeights(request.getRefrigeratorPowerCableWeights());
        urtReport.setupOilFromAggregatesWeights(request.getOilFromAggregatesWeights());
        urtReport.setupPsAbsRefrigeratorWeights(request.getPsAbsRefrigeratorWeights());
        urtReport.setupAggregatesWithOilWeights(request.getAggregatesWithOilWeights());
        urtReport.setupAluminiumWeights(request.getAluminiumWeights());
        urtReport.setupAggregatesWithOilFromWarehouseWeights(request.getAggregatesWithOilFromWarehouseWeights());

        urtReportRepository.save(urtReport);
    }

    /**
     * @param urtReportId - Input UrtReport's id.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    @Override
    public UrtReportDto fetchReportById(Long urtReportId) {
        UrtReport urtReport
                = urtReportRepository.findById(urtReportId)
                .orElseThrow(() -> new ResourceNotFoundException("UrtReport", "urtReportId", urtReportId.toString())
        );

        return mapUrtReportToDto(urtReport);
    }

    /**
     *
     * @param date - Date of report.
     * @param shift - Shift of report.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    @Override
    public UrtReportDto fetchReportByReportData(LocalDate date, Integer shift) {
        UrtReport urtReport
                = urtReportRepository.findByReportDate(new ReportDate(date, shift))
                .orElseThrow(
                    () -> new ResourceNotFoundException("urtReport", "reportData", new ReportDate(date,shift).toString())
                );

        return mapUrtReportToDto(urtReport);
    }

    /**
     *
     * @param urtReport - Input urtReport object.
     * @return - Return mapped UrtReportDto Object.
     */
    private UrtReportDto mapUrtReportToDto(UrtReport urtReport){
        return new UrtReportDto.UrtReportDtoBuilder()
                .reportData(urtReport.getReportDate())
                .leaders(urtReport.getLeaders())
                .brigade(urtReport.getBrigade())
                .forkliftOperators(urtReport.getForkliftOperators())
                .refrigeratorCount(urtReport.getRefrigeratorCount())
                .employeesCount(urtReport.getEmployeesCount())
                .atnWork(urtReport.getAtnWork())
                .robotWork(urtReport.getRobotWork())
                .reportHistories(urtReport.getUrtReportHistories())
                .aggregatesWithoutOilWeights(urtReport.getAggregatesWithoutOilWeights())
                .alCuRefrigeratorWeights(urtReport.getAlCuRefrigeratorWeights(), urtReport.getAlCuPackageIncompleteWeight())
                .oilFromAggregatesWeights(urtReport.getOilFromAggregatesWeights())
                .refrigeratorPowerCableWeights(urtReport.getRefrigeratorPowerCableWeights())
                .psAbsRefrigeratorWeights(urtReport.getPsAbsRefrigeratorWeights(), urtReport.getPsAbsRefrigeratorIncompleteWeight())
                .aggregatesWithOilWeights(urtReport.getAggregatesWithOilWeights())
                .aluminiumWeights(urtReport.getAluminiumWeights())
                .aggregatesWithOilFromWarehouseWeights(urtReport.getAggregatesWithOilFromWarehouseWeights())
                .build();
    }
}
