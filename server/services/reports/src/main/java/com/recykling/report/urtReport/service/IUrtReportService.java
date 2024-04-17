package com.recykling.report.urtReport.service;

import com.recykling.report.urtReport.controller.request.RequestCreateUrtReport;
import com.recykling.report.urtReport.dto.UrtReportDto;

import java.time.LocalDate;

/**
 * @author WiniaR21
 */
public interface IUrtReportService {
    /**
     *
     * @param request - Input RequestCreateUrtReport object.
     */
    void createReport(RequestCreateUrtReport request);

    /**
     *
     * @param urtReportId - Input UrtReport's id.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    UrtReportDto fetchReportById(Long urtReportId);

    /**
     *
     * @param date - Date of report.
     * @param shift - Shift of report.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    UrtReportDto fetchReportByReportData(LocalDate date, Integer shift);
}
