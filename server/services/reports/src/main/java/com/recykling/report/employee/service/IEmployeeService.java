package com.recykling.report.employee.service;

import com.recykling.report.employee.dto.EmployeeDto;
import com.recykling.report.employee.controller.request.RequestCreateEmployee;
import com.recykling.report.employee.controller.request.RequestUpdateEmployee;

import java.util.List;

/**
 * @author WiniaR21
 */
public interface IEmployeeService {

    /**
     *
     * @param request - Input RequestCreateEmployee object.
     */
    void createEmployee(RequestCreateEmployee request);

    /**
     *
     * @param employeeId - Unique id number of employee.
     * @return - Function returns matching employee by dto format.
     */
    EmployeeDto fetchEmployeeById(Long employeeId);

    /**
     *
     * @param firstName - Input Employee firstName.
     * @param lastName - Input Employee lastName/
     * @return - Function returns list of employees by dto format with matching firstName and lastName.
     */
    List<EmployeeDto> fetchEmployeeByFullName(String firstName, String lastName);

    /**
     *
     * @return All active employees.
     */
    List<EmployeeDto> fetchActiveEmployees();

    /**
     *
     * @return - All inactive employees.
     */
    List<EmployeeDto> fetchInactiveEmployees();

    /**
     *
     * @return - All employees.
     */
    List<EmployeeDto> fetchAllEmployees();

    /**
     *
     * @param request - Input RequestUpdateEmployee object.
     */
    void updateEmployee(RequestUpdateEmployee request);

}
