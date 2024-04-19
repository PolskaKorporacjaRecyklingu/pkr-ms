package com.recykling.report.urtReport;

import com.recykling.report.valueObjects.EmployeesCount;
import com.recykling.report.valueObjects.ReportDate;
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
    @Column(unique = true)
    private ReportDate reportDate;
    private EmployeesCount employeesCount;
}
