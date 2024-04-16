package com.recykling.report.entity.reports.urt.brigade;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.urt.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "urt_report_brigade")
@NoArgsConstructor
@Getter
@Setter
public class UrtBrigadeMember {
    @EmbeddedId
    private UrtEmployeeId urtEmployeeId = new UrtEmployeeId();

    @ManyToOne
    @MapsId("urtReportId")
    @JoinColumn(name = "urt_report_id")
    private UrtReport urtReport;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public UrtBrigadeMember(UrtReport urtReport, Employee employee) {
        this.urtReport = urtReport;
        this.employee = employee;
    }
}
