package com.recykling.report.controller;

import com.recykling.report.constans.EmployeeConstants;
import com.recykling.report.dto.EmployeeDto;
import com.recykling.report.request.RequestCreateEmployee;
import com.recykling.report.request.RequestUpdateEmployee;
import com.recykling.report.service.IEmployeeService;
import com.recykling.report.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createEmployee(
            @RequestBody @Valid RequestCreateEmployee request
    ){
        iEmployeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(EmployeeConstants.STATUS_201,EmployeeConstants.MESSAGE_201));
    }

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

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody RequestUpdateEmployee request){
        iEmployeeService.updateEmployee(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(EmployeeConstants.STATUS_200,EmployeeConstants.MESSAGE_200));
    }
}
