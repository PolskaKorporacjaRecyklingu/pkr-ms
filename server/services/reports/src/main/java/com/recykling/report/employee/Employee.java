package com.recykling.report.employee;

import com.recykling.report.urtReport.UrtReport;
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

    @Column(name = "has_account")
    public Boolean hasAccount;

    @ManyToMany(mappedBy = "brigade")
    private List<UrtReport> brigade = new ArrayList<>();

    @ManyToMany(mappedBy = "leaders")
    private List<UrtReport> leaders = new ArrayList<>();

    @ManyToMany(mappedBy = "forkliftOperators")
    private List<UrtReport> forkliftOperators = new ArrayList<>();

    /**
     * @BUILDER
     */
    private Employee(EmployeeBuilder employeeBuilder){
       this.employeeId = employeeBuilder.employeeId;
       this.fullName = employeeBuilder.fullName;
       this.active = employeeBuilder.active;
       this.hasAccount = employeeBuilder.hasAccount;

    }
    public static class EmployeeBuilder{
        private Long employeeId;
        private FullName fullName;
        private Boolean active;
        private Boolean hasAccount;
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
        public EmployeeBuilder hasAccount(Boolean hasAccount){
            this.hasAccount = hasAccount;
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }
    }

}
