package com.recykling.report.entity.reports.urt.brigade;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UrtEmployeeId implements Serializable {

    @Column(name = "urt_report_id")
    private Long urtReportId;

    @Column(name = "employee_id")
    private Long employeeId;

}
