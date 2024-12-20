package com.recykling.report.urtReport.service.impl;


import com.recykling.report.employee.Employee;
import com.recykling.report.exception.GivenLeaderHasNotAccount;
import com.recykling.report.exception.UniqueReportDateException;
import com.recykling.report.security.service.IJwtService;
import com.recykling.report.security.user.User;
import com.recykling.report.security.user.UserRepository;
import com.recykling.report.urtReport.service.IUrtReportService;
import com.recykling.report.urtReport.controller.request.RequestCreateUrtReport;
import com.recykling.report.urtReport.dto.UrtReportDto;
import com.recykling.report.urtReport.UrtReport;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.employee.repo.EmployeeRepository;
import com.recykling.report.urtReport.repo.UrtReportRepository;
import com.recykling.report.valueObjects.ReportDate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
    private final IJwtService jwtService;
    private final UserRepository userRepository;
    /**
     *
     * @param request - Input RequestCreateUrtReport object.
     * @NOTE - Function creates new urtReport based on request.
     *         First step to create is creating this entity with base data,
     *         second step is setting up all important info with are list of data.
     */
    @Override
    public void createReport(RequestCreateUrtReport request) {
        Optional<UrtReport> optionalUrtReport = urtReportRepository.findByReportDate(request.getReportDate());

        if (optionalUrtReport.isPresent()){
            throw new UniqueReportDateException(request.getReportDate());
        }
        UrtReport urtReport = buildReport(request);
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = details.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        urtReport.getLeaders().add(user.getEmployee());
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
     * @param date    - Date of report.
     * @param shift   - Shift of report.
     * @param request - Input RequestCreateUrtReport object.
     */
    @Override
    public UrtReportDto updateReport(LocalDate date, Integer shift, RequestCreateUrtReport request) {
        Optional<UrtReport> optionalUrtReport = urtReportRepository.findByReportDate(new ReportDate(date, shift));

        if (optionalUrtReport.isEmpty()){
            throw new ResourceNotFoundException("urtReport", "ReportData", new ReportDate(date, shift).toString());
        }

        UrtReport urtReport = buildReport(request);
        urtReportRepository.delete(optionalUrtReport.get());
        return mapUrtReportToDto(urtReportRepository.save(urtReport));

    }

    /**
     * @param date  - Date of report.
     * @param shift - Shift of report.
     */
    @Override
    public void deleteReport(LocalDate date, Integer shift) {
        Optional<UrtReport> optionalUrtReport = urtReportRepository.findByReportDate(new ReportDate(date, shift));
        if (optionalUrtReport.isEmpty()){
            throw new ResourceNotFoundException("urtReport", "ReportData", new ReportDate(date, shift).toString());
        }
        else {
            urtReportRepository.delete(optionalUrtReport.get());
        }
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
    private UrtReport buildReport(RequestCreateUrtReport request){
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

        return urtReport;
    }
}
