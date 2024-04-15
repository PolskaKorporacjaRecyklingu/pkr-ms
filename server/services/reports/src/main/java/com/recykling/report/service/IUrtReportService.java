package com.recykling.report.service;

import com.recykling.report.request.RequestCreateUrtReport;
import com.recykling.report.dto.UrtReportDto;
import com.recykling.report.valueObjects.Shift;

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
     * @param year - Year of report.
     * @param month - Month of report.
     * @param day - Day of report.
     * @param shift - Shift of report.
     * @return - Returns matching UrtReport in UrtReportDto format.
     */
    UrtReportDto fetchReportByReportData(Integer year, Integer month, Integer day, Shift shift);
}
