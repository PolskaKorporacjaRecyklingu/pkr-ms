package com.recykling.report.entity.employee;

import com.recykling.report.entity.reports.urt.UrtReport;
import com.recykling.report.entity.reports.urt.brigade.UrtBrigadeMember;
import com.recykling.report.valueObjects.FullName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author WiniaR21
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employee_id")
    private Long employeeId;

    private FullName fullName;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<UrtBrigadeMember> brigade = new ArrayList<>();

    public void addToBrigade(UrtBrigadeMember urtBrigadeMember){
        if(!brigade.contains(urtBrigadeMember)){
            brigade.add(urtBrigadeMember);
        }
    }
    public void removeFromBrigade(UrtBrigadeMember urtBrigadeMember){
        brigade.remove(urtBrigadeMember);
    }
    /**
     * @BUILDER
     */
    private Employee(EmployeeBuilder employeeBuilder){
       this.employeeId = employeeBuilder.employeeId;
       this.fullName = employeeBuilder.fullName;
       this.active = employeeBuilder.active;
    }
    public static class EmployeeBuilder{
        private Long employeeId;
        private FullName fullName;
        private Boolean active;
        public EmployeeBuilder employeeId(Long employeeId){
            this.employeeId = employeeId;
            return this;
        }
        public EmployeeBuilder fullName(FullName fullName){
            this.fullName = fullName;
            return this;
        }
        public EmployeeBuilder active(Boolean active){
            this.active = active;
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }
    }

}
