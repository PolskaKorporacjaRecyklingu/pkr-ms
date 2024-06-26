package com.recykling.report.employee.controller;


import com.recykling.report.employee.dto.EmployeeDto;
import com.recykling.report.employee.controller.request.RequestCreateEmployee;
import com.recykling.report.employee.controller.request.RequestUpdateEmployee;
import com.recykling.report.employee.service.IEmployeeService;
import com.recykling.report.exception.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WiniaR21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/employee/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class EmployeeController {
    private final IEmployeeService iEmployeeService;

    @Secured({"MANAGER", "ADMIN"})
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createEmployee(
            @RequestBody @Valid RequestCreateEmployee request
    ){
        iEmployeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Employee created successfully")); 
    }

    @Secured({"URT_LEADER","MANAGER", "ADMIN"})
    @GetMapping(path = "/fetch")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(
            @RequestParam Long employeeId
    )
    {
        EmployeeDto employeeDto = iEmployeeService.fetchEmployeeById(employeeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeDto);
    }

    @Secured({"URT_LEADER","MANAGER", "ADMIN"})
    @GetMapping(path = "/fetch-by-fullName")
    public ResponseEntity<List<EmployeeDto>> fetchEmployeeByFullName(
            @RequestParam String firstName,
            @RequestParam String lastName
    )
    {
        List<EmployeeDto> employeesDto = iEmployeeService.fetchEmployeeByFullName(firstName, lastName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesDto);
    }

    @Secured({"URT_LEADER","MANAGER", "ADMIN"})
    @GetMapping(path = "/fetch-all")
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployees(){
        List<EmployeeDto> employeesDto = iEmployeeService.fetchAllEmployees();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesDto);
    }

    @Secured({"URT_LEADER","MANAGER", "ADMIN"})
    @GetMapping(path = "/fetch-all-active")
    public ResponseEntity<List<EmployeeDto>> fetchActiveEmployees(){
        List<EmployeeDto> employeesDto = iEmployeeService.fetchActiveEmployees();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesDto);
    }
    @Secured({"URT_LEADER","MANAGER", "ADMIN"})
    @GetMapping(path = "/fetch-all-inactive")
    public ResponseEntity<List<EmployeeDto>> fetchInactiveEmployees(){
        List<EmployeeDto> employeesDto = iEmployeeService.fetchInactiveEmployees();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeesDto);
    }
    @Secured({"MANAGER", "ADMIN"})
    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateEmployee(
            @Valid @RequestBody RequestUpdateEmployee request,
            @NotNull @RequestParam Long employeeId)
    {
        iEmployeeService.updateEmployee(request, employeeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Request proceed successfully"));
    }


}
