package com.recykling.report.employee.service.impl;

import com.recykling.report.employee.Employee;
import com.recykling.report.employee.dto.EmployeeDto;
import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.employee.repo.EmployeeRepository;
import com.recykling.report.employee.controller.request.RequestCreateEmployee;
import com.recykling.report.employee.controller.request.RequestUpdateEmployee;
import com.recykling.report.employee.service.IEmployeeService;
import com.recykling.report.valueObjects.FullName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WiniaR21
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    /**
     *
     * @param request - Input RequestCreateEmployee object.
     */
    @Override
    public void createEmployee(RequestCreateEmployee request) {
        Employee employee = new Employee.EmployeeBuilder()
                .fullName(request.getFullName())
                .active(true)
                .hasAccount(false)
                .build();

        employeeRepository.save(employee);
    }

    /**
     *
     * @param employeeId - Unique id number of employee.
     * @return - Function returns matching employee by dto format.
     */
    @Override
    public EmployeeDto fetchEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(
                () -> new ResourceNotFoundException("Employee", "employeeId", employeeId.toString())
        );

        return new EmployeeDto.EmployeeDtoBuilder()
                .fullName(employee.getFullName())
                .employeeId(employee.getEmployeeId())
                .active(employee.getActive())
                .build();
    }

    /**
     *
     * @param firstName - Input Employee firstName.
     * @param lastName  - Input Employee lastName.
     * @return - Function returns list of employees by dto format with matching firstName and lastName.
     */
    @Override
    public List<EmployeeDto> fetchEmployeeByFullName(String firstName, String lastName) {

        FullName fullName = new FullName(firstName, lastName);
        List<Employee> employees = employeeRepository.findAllByFullName(fullName);

        if(employees.isEmpty()){
            throw new ResourceNotFoundException
                    ("Employees","firstName and lastName", firstName + " " + lastName);
        }

        List<EmployeeDto> employeesDto = new ArrayList<>();
        employees.forEach(employee -> employeesDto.add(buildEmployeeDto(employee)));

        return employeesDto;
    }

    /**
     *
     * @return - All active employees.
     */
    @Override
    public List<EmployeeDto> fetchActiveEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employeeRepository
                .findAllByActive(true)
                .forEach(employee -> employeesDto.add(buildEmployeeDto(employee)));
        return employeesDto;
    }

    /**
     *
     * @return - All inactive employees.
     */
    @Override
    public List<EmployeeDto> fetchInactiveEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employeeRepository
                .findAllByActive(false)
                .forEach(employee -> employeesDto.add(buildEmployeeDto(employee)));

        return employeesDto;
    }

    /**
     *
     * @return - All employees.
     */
    @Override
    public List<EmployeeDto> fetchAllEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employeeRepository.findAll()
                .forEach(employee ->
                employeesDto.add(buildEmployeeDto(employee)));

        return employeesDto;
    }

    /**
     *
     * @param request - Input RequestUpdateEmployee object.
     */
    @Override
    public void updateEmployee(RequestUpdateEmployee request, Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","employeeId", employeeId.toString())
        );

        Employee employee = new Employee.EmployeeBuilder()
                .employeeId(employeeId)
                .fullName(request.getFullName())
                .active(request.getActive())
                .build();

        employeeRepository.save(employee);
    }

    public EmployeeDto buildEmployeeDto(Employee employee){
        return new EmployeeDto.EmployeeDtoBuilder()
                .employeeId(employee.getEmployeeId())
                .fullName(employee.getFullName())
                .active(employee.getActive())
                .hasAccount(employee.getHasAccount())
                .build();
    }
}
