package com.recykling.report.service.impl;

import com.recykling.report.entity.reports.urt.brigade.UrtBrigadeMember;
import com.recykling.report.entity.reports.urt.brigade.UrtForkliftOperator;
import com.recykling.report.entity.reports.urt.brigade.UrtReportLieder;
import com.recykling.report.request.RequestCreateUrtReport;
import com.recykling.report.dto.UrtReportDto;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.urt.UrtReport;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.repository.EmployeeRepository;
import com.recykling.report.repository.UrtReportRepository;
import com.recykling.report.service.IUrtReportService;
import com.recykling.report.valueObjects.EmployeesCount;
import com.recykling.report.valueObjects.ReportData;
import com.recykling.report.valueObjects.Shift;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Employee> brigade = new ArrayList<>();
        List<Employee> leaders = new ArrayList<>();
        List<Employee> forkliftOperators = new ArrayList<>();

        request.getBrigadeEmployeesIdList().forEach(employeeId ->{
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isEmpty()){
                throw new ResourceNotFoundException("Employee", "employeeId", employeeId.toString());
            }else {
                brigade.add(employee.get());
            }
        });

        request.getLeadersId().forEach(leaderId -> {
            Optional<Employee> leader = employeeRepository.findById(leaderId);
            if (leader.isEmpty()){
                throw new ResourceNotFoundException("Employee", "leaderId", leaderId.toString());
            } else {
                leaders.add(leader.get());
            }
        });

        request.getForkliftOperatorsId().forEach(forkliftOperatorId -> {
            Optional<Employee> forkliftOperator = employeeRepository.findById(forkliftOperatorId);
            if (forkliftOperator.isEmpty()){
                throw new ResourceNotFoundException("Employee", "forkliftOperatorId", forkliftOperatorId.toString());
            } else {
                forkliftOperators.add(forkliftOperator.get());
            }
        });

        UrtReport urtReport = new UrtReport.ReportBuilder(urtReportRepository)
                .reportData(request.getReportData())
                .employeesCount(new EmployeesCount(brigade.size()))
                .refrigeratorCount(request.getRefrigeratorCount())
                .robotWork(request.getRobotWork())
                .atnWork(request.getAtnWork())
                .build();

        brigade.forEach(employee -> new UrtBrigadeMember(urtReport, employee));
        leaders.forEach(employee -> new UrtReportLieder(urtReport, employee));
        forkliftOperators.forEach(employee -> new UrtForkliftOperator(urtReport, employee));

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
                .reportData(urtReport.getReportData())
                .leaders(urtReport.getLeaders())
                .forkliftOperator(urtReport.getForkliftOperators())
                .refrigeratorCount(urtReport.getRefrigeratorCount())
                .robotWork(urtReport.getRobotWork())
                .atnWork(urtReport.getAtnWork())
                .employeesCount(urtReport.getEmployeesCount())
                .brigade(urtReport.getBrigade())
                .build();
    }

    /**
     * @param year  - Year of report.
     * @param month - Month of report.
     * @param day   - Day of report.
     * @param shift - Shift of report.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    @Override
    public UrtReportDto fetchReportByReportData(Integer year, Integer month, Integer day, Integer shift) {
        ReportData reportData = new ReportData(year, month, day, new Shift(shift));
        System.out.println(reportData);

        UrtReport urtReport = urtReportRepository.findByReportData(reportData).orElseThrow(
                () -> new ResourceNotFoundException("urtReport", "reportData", reportData.toString())
        );
        return new UrtReportDto.UrtReportDtoBuilder()
                .reportData(urtReport.getReportData())
                .leaders(urtReport.getLeaders())
                .forkliftOperator(urtReport.getForkliftOperators())
                .refrigeratorCount(urtReport.getRefrigeratorCount())
                .robotWork(urtReport.getRobotWork())
                .atnWork(urtReport.getAtnWork())
                .employeesCount(urtReport.getEmployeesCount())
                .brigade(urtReport.getBrigade())
                .build();
    }
}
