package com.recykling.report.entity.reports.urt.brigade;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.urt.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "urt_report_forklift_operator")
@NoArgsConstructor
@Getter
@Setter
public class UrtForkliftOperator {
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

    public UrtForkliftOperator(UrtReport urtReport, Employee employee) {
        this.urtReport = urtReport;
        this.employee = employee;

        setInReport();
        setInEmployee();
    }
    private void setInReport(){
        if(!urtReport.getForkliftOperators().contains(this)){
            urtReport.getForkliftOperators().add(this);
        }
    }
    private void setInEmployee(){
        if(!employee.getForkliftOperators().contains(this)){
            employee.getForkliftOperators().add(this);
        }
    }
    public void remove(){
        urtReport.getForkliftOperators().remove(this);
        employee.getForkliftOperators().remove(this);
    }
}
