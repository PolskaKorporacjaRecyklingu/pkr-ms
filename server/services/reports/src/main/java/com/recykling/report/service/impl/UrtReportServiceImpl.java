package com.recykling.report.service.impl;

import com.recykling.report.request.RequestCreateUrtReport;
import com.recykling.report.dto.UrtReportDto;

import com.recykling.report.entity.reports.UrtReport;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.repository.EmployeeRepository;
import com.recykling.report.repository.UrtReportRepository;
import com.recykling.report.service.IUrtReportService;
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
     * @param request - Input RequestCreateUrtReport object.
     */
    @Override
    public void createReport(RequestCreateUrtReport request) {

        UrtReport urtReport = new UrtReport.ReportBuilder(employeeRepository)
                .reportData(request.getReportDate())
                .leaders(request.getLeadersId())
                .forkliftOperators(request.getForkliftOperatorsId())
                .brigade(request.getBrigadeEmployeesIdList())
                .refrigeratorCount(request.getRefrigeratorCount())
                .robotWork(request.getRobotWork())
                .atnWork(request.getAtnWork())
                .build();

        urtReportRepository.save(urtReport);
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
