package com.recykling.report.entity.reports;

import com.recykling.report.valueObjects.EmployeesCount;
import com.recykling.report.valueObjects.ReportData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WiniaR21
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public class ReportBase {
    private ReportData reportData;
    private EmployeesCount employeesCount;
}
