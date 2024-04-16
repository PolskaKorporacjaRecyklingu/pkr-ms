package com.recykling.report.controller;

import com.recykling.report.request.RequestCreateUrtReport;
import com.recykling.report.dto.UrtReportDto;
import com.recykling.report.service.IUrtReportService;
import com.recykling.report.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDto> createReport(@RequestBody @Valid RequestCreateUrtReport request){
        iUrtReportService.createReport(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "created"));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<UrtReportDto> fetchReportById(@RequestParam Long urtReportId){
        UrtReportDto urtReportDto = iUrtReportService.fetchReportById(urtReportId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(urtReportDto);
    }

    @GetMapping(path = "/fetch-by-data")
    public ResponseEntity<UrtReportDto> fetchReportByReportData(
            @NotNull @Valid @RequestParam Integer year,
            @NotNull @Valid @RequestParam Integer month,
            @NotNull @Valid @RequestParam Integer day,
            @NotNull @Valid @RequestParam Integer shift

    ){
        UrtReportDto urtReportDto = iUrtReportService.fetchReportByReportData(
                year,
                month,
                day,
                shift
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(urtReportDto);
    }
}
