package com.recykling.report.exception;

import com.recykling.report.valueObjects.ReportDate;

public class UniqueRaportDateException extends RuntimeException{
    public UniqueRaportDateException(ReportDate reportDate) {
        super("Report with date " + reportDate.toString() + " already exist.");
    }
}
