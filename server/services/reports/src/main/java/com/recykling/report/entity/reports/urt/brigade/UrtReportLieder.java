package com.recykling.report.entity.reports.urt.brigade;

import com.recykling.report.entity.employee.Employee;
import com.recykling.report.entity.reports.urt.UrtReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "urt_report_lieder")
@NoArgsConstructor
@Getter
@Setter
public class UrtReportLieder {
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

    public UrtReportLieder(UrtReport urtReport, Employee employee) {
        this.urtReport = urtReport;
        this.employee = employee;

        setInReport();
        setInEmployee();
    }
    private void setInReport(){
        if(!urtReport.getLeaders().contains(this)){
            urtReport.getLeaders().add(this);
        }
    }
    private void setInEmployee(){
        if(!employee.getLeaders().contains(this)){
            employee.getLeaders().add(this);
        }
    }
    public void remove(){
        urtReport.getLeaders().remove(this);
        employee.getLeaders().remove(this);
    }
}
