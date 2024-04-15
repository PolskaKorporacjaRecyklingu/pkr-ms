package com.recykling.report.service.impl;

import com.recykling.report.request.RequestCreateUrtReport;
import com.recykling.report.dto.UrtReportDto;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.urtReport.UrtReport;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.repository.EmployeeRepository;
import com.recykling.report.repository.UrtReportRepository;
import com.recykling.report.service.IUrtReportService;
import com.recykling.report.valueObjects.EmployeesCount;
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

        Employee lieder = employeeRepository.findById(request.getLiederId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "liederId", request.getLiederId().toString())
        );

        request.getBrigadeEmployeesIdList().forEach(employeeId ->{
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isEmpty()){
                throw new ResourceNotFoundException("Employee", "employeeId", employeeId.toString());
            }else {
                brigade.add(employee.get());
            }
        });


        UrtReport urtReport = new UrtReport.ReportBuilder()
                .reportData(request.getReportData())
                .lieder(lieder)
                .employeesCount(new EmployeesCount(brigade.size()))
                .refrigeratorCount(request.getRefrigeratorCount())
                .robotWork(request.getRobotWork())
                .atnWork(request.getAtnWork())
                .build();

        urtReport.createBrigade(brigade);

        if (request.getForkliftOperatorId() != null) {
            Employee forkliftOperator = employeeRepository.findById(request.getForkliftOperatorId()).orElseThrow(
                    () -> new ResourceNotFoundException("Employee", "forkliftOperatorId", request.getForkliftOperatorId().toString())
            );
            urtReport.setForkliftOperator(forkliftOperator);
        }
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
                .lieder(urtReport.getLieder())
                .forkliftOperator(urtReport.getForkliftOperator())
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
    public UrtReportDto fetchReportByReportData(Integer year, Integer month, Integer day, Shift shift) {
        return null;
    }
}
