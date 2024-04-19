package com.recykling.report.employee.repo;

import com.recykling.report.employee.Employee;
import com.recykling.report.valueObjects.FullName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WiniaR21
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByFullName(FullName fullName);
    List<Employee> findAllByActive(Boolean active);
}
