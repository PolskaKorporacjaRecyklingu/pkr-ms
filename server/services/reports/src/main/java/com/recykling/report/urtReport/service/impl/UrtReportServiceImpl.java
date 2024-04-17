package com.recykling.report.urtReport.service.impl;

import com.recykling.report.urtReport.entities.alCuRefrigerator.AlCuRefrigeratorWeights;
import com.recykling.report.urtReport.entities.alCuRefrigerator.repo.AlCuRefrigeratorWeightsRepository;
import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.OilFromAggregatesWeights;
import com.recykling.report.urtReport.entities.oilFromAggregatesWeights.repo.OilFromAggregatesWeightsRepository;
import com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.RefrigeratorPowerCableWeights;
import com.recykling.report.urtReport.entities.refrigeratorPowerCableWeights.repo.RefrigeratorPowerCableWeightsRepository;
import com.recykling.report.urtReport.service.IUrtReportService;
import com.recykling.report.urtReport.entities.urtReportHistory.UrtReportHistory;
import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.AggregatesWithoutOilWeights;
import com.recykling.report.urtReport.entities.urtAggregatesWithoutOilWeights.repo.AggregatesWithoutOilWeightsRepository;
import com.recykling.report.urtReport.entities.urtReportHistory.repo.UrtReportHistoryRepository;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author WiniaR21
 */
@Service
@RequiredArgsConstructor
public class UrtReportServiceImpl implements IUrtReportService {
    private final UrtReportRepository urtReportRepository;
    private final EmployeeRepository employeeRepository;
    private final UrtReportHistoryRepository urtReportHistoryRepository;
    private final AggregatesWithoutOilWeightsRepository aggregatesWIthoutOilWeightsRepository;
    private final AlCuRefrigeratorWeightsRepository alCuRefrigeratorWeightsRepository;
    private final RefrigeratorPowerCableWeightsRepository refrigeratorPowerCableWeightsRepository;
    private final OilFromAggregatesWeightsRepository oilFromAggregatesWeightsRepository;
    /**
     * @param request - Input RequestCreateUrtReport object.
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
                .build();

        urtReportRepository.save(urtReport);

        setupReportHistory(request,urtReport);
        setupAggregatesWithoutOilWeights(request.getAggregatesWithoutOilWeights(),urtReport);
        setupAlCuRefrigeratorWeights(request.getAlCuRefrigeratorWeights(),urtReport);
        setupOilFromAggregatesWeights(request.getOilFromAggregatesWeights(),urtReport);
        setupRefrigeratorPowerCableWeights(request.getRefrigeratorPowerCableWeights(),urtReport);

    }
    private void setupReportHistory(RequestCreateUrtReport request, UrtReport urtReport){
        List<UrtReportHistory> reportHistories = new ArrayList<>();
        request.getReportHistories()
                .forEach(history -> reportHistories.add(new UrtReportHistory(history,urtReport)));
        urtReportHistoryRepository.saveAll(reportHistories);
    }
    private void setupAggregatesWithoutOilWeights(List<Integer> weights, UrtReport urtReport){
        List<AggregatesWithoutOilWeights> aggregatesWithoutOilWeights = new ArrayList<>();
        weights.forEach(weight -> aggregatesWithoutOilWeights.add(new AggregatesWithoutOilWeights(weight, urtReport)));
        aggregatesWIthoutOilWeightsRepository.saveAll(aggregatesWithoutOilWeights);
    }
    private void setupAlCuRefrigeratorWeights(List<Integer> weights, UrtReport urtReport){
        List<AlCuRefrigeratorWeights> alCuRefrigeratorWeights = new ArrayList<>();
        weights.forEach(weight -> alCuRefrigeratorWeights.add(new AlCuRefrigeratorWeights(weight, urtReport)));
        alCuRefrigeratorWeightsRepository.saveAll(alCuRefrigeratorWeights);
    }
    private void setupRefrigeratorPowerCableWeights(List<Integer> weights, UrtReport urtReport){
        List<RefrigeratorPowerCableWeights> refrigeratorPowerCableWeights = new ArrayList<>();
        weights.forEach(weight -> refrigeratorPowerCableWeights.add(new RefrigeratorPowerCableWeights(weight, urtReport)));
        refrigeratorPowerCableWeightsRepository.saveAll(refrigeratorPowerCableWeights);
    }
    private void setupOilFromAggregatesWeights(List<Integer> weights, UrtReport urtReport){
        List<OilFromAggregatesWeights> oilFromAggregatesWeights = new ArrayList<>();
        weights.forEach(weight -> oilFromAggregatesWeights.add(new OilFromAggregatesWeights(weight,urtReport)));
        oilFromAggregatesWeightsRepository.saveAll(oilFromAggregatesWeights);
    }

    /**
     * @param urtReportId - Input UrtReport's id.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    @Override
    public UrtReportDto fetchReportById(Long urtReportId) {
        UrtReport urtReport = urtReportRepository.findById(urtReportId).orElseThrow(
                () -> new ResourceNotFoundException("UrtReport", "urtReportId", urtReportId.toString())
        );
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
                .aggregatesWithoutOil(urtReport.getAggregatesWithoutOil())
                .alCuRefrigeratorWeights(urtReport.getAlCuRefrigeratorWeights(), urtReport.getAlCuPackageIncompleteWeight())
                .oilFromAggregatesWeights(urtReport.getOilFromAggregatesWeights())
                .refrigeratorPowerCableWeights(urtReport.getRefrigeratorPowerCableWeights())
                .build();
    }

    /**
     *
     * @param date - Date of report.
     * @param shift - Shift of report.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    @Override
    public UrtReportDto fetchReportByReportData(LocalDate date, Integer shift) {
        ReportDate reportDate = new ReportDate(date, shift);

        UrtReport urtReport = urtReportRepository.findByReportDate(reportDate).orElseThrow(
                () -> new ResourceNotFoundException("urtReport", "reportData", reportDate.toString())
        );

        return new UrtReportDto.UrtReportDtoBuilder()
                .reportData(urtReport.getReportDate())
                .leaders(urtReport.getLeaders())
                .forkliftOperators(urtReport.getForkliftOperators())
                .refrigeratorCount(urtReport.getRefrigeratorCount())
                .brigade(urtReport.getBrigade())
                .robotWork(urtReport.getRobotWork())
                .atnWork(urtReport.getAtnWork())
                .employeesCount(urtReport.getEmployeesCount())
                .build();
    }
}
