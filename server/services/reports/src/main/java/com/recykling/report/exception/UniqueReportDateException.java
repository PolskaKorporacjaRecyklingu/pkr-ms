package com.recykling.report.exception;

import com.recykling.report.valueObjects.ReportDate;

public class UniqueReportDateException extends RuntimeException{
    public UniqueReportDateException(ReportDate reportDate) {
        super("Report with date " + reportDate.toString() + " already exist.");
    }
}
