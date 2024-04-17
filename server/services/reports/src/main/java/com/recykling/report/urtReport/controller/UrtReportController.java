package com.recykling.report.urtReport.controller;

import com.recykling.report.urtReport.controller.request.RequestCreateUrtReport;
import com.recykling.report.urtReport.dto.UrtReportDto;
import com.recykling.report.urtReport.service.IUrtReportService;
import com.recykling.report.exception.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author WiniaR21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/reports/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UrtReportController {
    private final IUrtReportService iUrtReportService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createReport(@RequestBody @Valid @NotNull RequestCreateUrtReport request){
        iUrtReportService.createReport(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "created"));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<UrtReportDto> fetchReportById(
            @NotNull(message = "UrtReportId can not be null")
            @Valid @RequestParam Long urtReportId
    ){
        UrtReportDto urtReportDto = iUrtReportService.fetchReportById(urtReportId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(urtReportDto);
    }

    @GetMapping(path = "/fetch-by-data")
    public ResponseEntity<UrtReportDto> fetchReportByReportData(

            @NotNull(message = "Date can not be null")
            @Valid @RequestParam LocalDate date,

            @NotNull(message = "Shift can not be null")
            @Min(value = 1, message = "There are 3 shifts")
            @Max(value = 3, message = "There are 3 shifts")
            @Valid @RequestParam Integer shift
    ){
        UrtReportDto urtReportDto = iUrtReportService.fetchReportByReportData(
                date,
                shift
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(urtReportDto);
    }
}